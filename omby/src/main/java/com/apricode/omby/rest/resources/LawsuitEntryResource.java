package com.apricode.omby.rest.resources;

import java.io.IOException;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import java.util.Set;

import javax.ws.rs.Consumes;
import javax.ws.rs.DELETE;
import javax.ws.rs.GET;
import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;
import javax.ws.rs.WebApplicationException;
import javax.ws.rs.core.MediaType;

import com.apricode.omby.transfer.LawsuitTransfer;
import com.apricode.omby.view.JsonViews;
import com.apricode.omby.dao.LawsuitDao;
import com.apricode.omby.dao.RoleDao;
import com.apricode.omby.dao.UserDao;
import com.apricode.omby.domain.Lawsuit;
import com.apricode.omby.domain.OmbyRuleException;
import com.apricode.omby.domain.Role;
import com.apricode.omby.domain.User;
import com.apricode.omby.domain.UserLawsuit;

import org.codehaus.jackson.JsonGenerationException;
import org.codehaus.jackson.annotate.JsonAutoDetect.Visibility;
import org.codehaus.jackson.annotate.JsonIgnoreProperties;
import org.codehaus.jackson.annotate.JsonMethod;
import org.codehaus.jackson.map.JsonMappingException;
import org.codehaus.jackson.map.ObjectMapper;
import org.codehaus.jackson.map.ObjectWriter;
import org.codehaus.jackson.map.SerializationConfig.Feature;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;


@Component
@Path("/lawsuit")
public class LawsuitEntryResource
{

	private final Logger logger = LoggerFactory.getLogger(this.getClass());

	
	@Autowired
	private LawsuitDao lawsuitDao;
	@Autowired
	private UserDao userDao;
	@Autowired
	private RoleDao roleDao;
	
	
	@Autowired
	private ObjectMapper mapper;


	
	@GET
	@Produces(MediaType.APPLICATION_JSON)
	@Transactional
	public String list() throws JsonGenerationException, JsonMappingException, IOException
	{
		this.logger.info("list()");

		
		ObjectWriter viewWriter;
		if (this.isAdmin()) {
			viewWriter = this.mapper.writerWithView(JsonViews.Admin.class);
		} else {
			viewWriter = this.mapper.writerWithView(JsonViews.User.class);
		}
		
		
		Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
		Object principal = authentication.getPrincipal();
		UserDetails userDetails = (UserDetails) principal;		
		
		User currentUser = userDao.findByEmail(userDetails.getUsername());				
		List<LawsuitTransfer> allEntries =  new ArrayList<LawsuitTransfer>();
		
		for (UserLawsuit ul:currentUser.getLawsuitUsers() ){			
			Lawsuit ls = ul.getLawsuit();
			LawsuitTransfer lst = new LawsuitTransfer();
			lst.setId(ls.getId());
			lst.setName(ls.getName());
			lst.setPublicLawsuit(ls.getPublicLawsuit());
			lst.setVersion(ls.getVersion());
			
			
			logger.info ("Lawsuit Name: " + lst.getName() + " Id: " + lst.getId() );
			allEntries.add (lst );			
		}
		return viewWriter.writeValueAsString(allEntries);
	}

	@GET
	@Produces(MediaType.APPLICATION_JSON)
	@Path("{id}")
	public LawsuitTransfer read(@PathParam("id") Long id)
	{
		this.logger.info("read(id)");

		Lawsuit ls = this.lawsuitDao.find(id);
		if (ls == null) {
			throw new WebApplicationException(404);
		}
		
		LawsuitTransfer lst = new LawsuitTransfer();
		lst.setId(ls.getId());
		lst.setName(ls.getName());
		lst.setPublicLawsuit(ls.getPublicLawsuit());
		lst.setVersion(ls.getVersion());
		return lst;
	}


	@POST
	@Produces(MediaType.APPLICATION_JSON)
	@Consumes(MediaType.APPLICATION_JSON)
	public LawsuitTransfer create(LawsuitTransfer lawsuitTransferEntry)
	{
		this.logger.info("create(): " + lawsuitTransferEntry);
		
		
		Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
		Object principal = authentication.getPrincipal();
		UserDetails userDetails = (UserDetails) principal;		
		
		User currentUser = userDao.findByEmail(userDetails.getUsername());	
		
		Lawsuit lawsuit =  this.lawsuitDao.save(LawsuitTransfer.map(lawsuitTransferEntry) );
		
		
		try {
			
			Role  suerRole = this.roleDao.findByName(Role.SUER);
			lawsuit.addUser(currentUser, suerRole);
			lawsuit = this.lawsuitDao.save(lawsuit);
			currentUser = this.userDao.save(currentUser);



			
		} catch (OmbyRuleException e) {
			e.printStackTrace();
		}
		

		return LawsuitTransfer.map(lawsuit);
	}


	@POST
	@Produces(MediaType.APPLICATION_JSON)
	@Consumes(MediaType.APPLICATION_JSON)
	@Path("{id}")
	public LawsuitTransfer update(@PathParam("id") Long id, LawsuitTransfer lawsuitTransferEntry)
	{
		this.logger.info("update(): " + lawsuitTransferEntry);
		
		Lawsuit ls = this.lawsuitDao.find(id);
		ls.setName(lawsuitTransferEntry.getName());
		
		ls = this.lawsuitDao.save(ls);
		
		return LawsuitTransfer.map(ls);
	}




	@DELETE
	@Produces(MediaType.APPLICATION_JSON)
	@Path("{id}")
	public void delete(@PathParam("id") Long id)
	{
		this.logger.info("delete(id)");

		this.lawsuitDao.delete(id);
	}


	private boolean isAdmin()
	{
		
		return true; //FIXME delete later
//		Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
//		Object principal = authentication.getPrincipal();
//		if (principal instanceof String && ((String) principal).equals("anonymousUser")) {
//			return false;
//		}
//		UserDetails userDetails = (UserDetails) principal;
//
//		for (GrantedAuthority authority : userDetails.getAuthorities()) {
//			if (authority.toString().equals("admin")) {
//				return true;
//			}
//		}
//
//		return false;
	}

}