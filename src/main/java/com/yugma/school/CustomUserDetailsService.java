package com.yugma.school;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service("customUserDetailsService")
@Transactional
public class CustomUserDetailsService implements UserDetailsService{
   
	/*@Autowired
	ParentDao parentDao;
	
	@Autowired
	EmployeeDao employeeDao;
	
	@Autowired
	EmployeeManagementDao employeeManagementDao;*/
	
	public UserDetails loadUserByUsername(String username)
            throws UsernameNotFoundException {
		String regexp = "^[A-Za-z_][A-Za-z0-9_]{4,20}$";
		Pattern p = Pattern.compile(regexp);
		username = username.toLowerCase();
	    Matcher m = p.matcher(username);
	    Map<String,Object> user = null;
	   
	    	if(username.startsWith("0"))
	    		username = username.substring(1);
	    	//user = parentDao.fetchLoginDetailByContact(username);
	    	if(user==null){
	    		System.out.println("User not found");
	            throw new UsernameNotFoundException("Username not found");
	    	}
	    	return new org.springframework.security.core.userdetails.User((String)user.get("username")
            		,(String)user.get("password"),true, true, true, true, getGrantedAuthoritiesForParent());
	    
    }
 
     
    private List<GrantedAuthority> getGrantedAuthoritiesForParent(){
        List<GrantedAuthority> authorities = new ArrayList<GrantedAuthority>();
        authorities.add(new SimpleGrantedAuthority("ROLE_PARENT"));
        
        return authorities;
    }
    
}