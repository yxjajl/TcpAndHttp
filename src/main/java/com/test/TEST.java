package com.test;

import java.util.Enumeration;
import java.util.Hashtable;
import java.util.UUID;

import javax.naming.Context;
import javax.naming.NamingEnumeration;
import javax.naming.NamingException;
import javax.naming.directory.Attribute;
import javax.naming.directory.Attributes;
import javax.naming.directory.DirContext;
import javax.naming.directory.SearchControls;
import javax.naming.directory.SearchResult;
import javax.naming.ldap.InitialLdapContext;
import javax.naming.ldap.LdapContext;

public class TEST {

	public static void main(String args[]) {

		String lcf = "com.sun.jndi.ldap.LdapCtxFactory";
		String ldapurl = "ldap://10.142.5.103:389/";
		String loginid = "sAMAccountName=awchen,CN=Alex Chen,OU=IT,OU=CIGNA-CMC Empolyees"; // samaccountname  
		String domain = "DC=cmc-xinnuo,DC=com";
		String password = "Ww123456";
		DirContext ctx = null;
		Hashtable env = new Hashtable();
		Attributes attr = null;
		Attributes resultsAttrs = null;
		SearchResult result = null;
		NamingEnumeration results = null;
		int iResults = 0;

		env.put(Context.INITIAL_CONTEXT_FACTORY, lcf);
		env.put(Context.SECURITY_AUTHENTICATION, "simple");
		env.put(Context.PROVIDER_URL, ldapurl);
		env.put(Context.SECURITY_PRINCIPAL, loginid + "," + domain);
		env.put(Context.SECURITY_CREDENTIALS, password);
//		env.put("java.naming.ldap.attributes.binary", "objectSid objectGUID");
		LdapContext ldapCtx = null;
		try {
			ldapCtx = new InitialLdapContext(env, null);
			queryGroup(ldapCtx);
			// queryUser(ldapCtx);

		} catch (NamingException e) {
			e.printStackTrace();
		} finally {
			if (ldapCtx != null) {
				try {
					ldapCtx.close();
				} catch (NamingException e) {
				}
			}
		}

//		try {
//
//			ctx = new InitialDirContext(env);
//			attr = new BasicAttributes(true);
//			attr.put(new BasicAttribute("samaccountname", loginid));
//			results = ctx.search("", attr);
//			while (results.hasMore()) {
//				result = (SearchResult) results.next();
//				resultsAttrs = result.getAttributes();
//
//				for (NamingEnumeration enumAttributes = resultsAttrs.getAll(); enumAttributes.hasMore();) {
//					Attribute a = (Attribute) enumAttributes.next();
//					System.out.println("attribute: " + a.getID() + " : " + a.get().toString());
//
//				} // end for loop
//
//				iResults++;
//			} // end while loop
//
//			System.out.println("iResults == " + iResults);
//
//		} // end try
//		catch (Exception e) {
//			e.printStackTrace();
//		}

	}// end function main()

	private static void queryGroup(LdapContext ldapCtx) throws NamingException {
		SearchControls searchCtls = new SearchControls();
		searchCtls.setSearchScope(SearchControls.SUBTREE_SCOPE);
		String searchFilter = "objectClass=organizationalUnit";
		String searchBase = "ou=myDeptSubDept,ou=myDept,dc=DS-66,dc=com";
		String returnedAtts[] = { "distinguishedName", "objectGUID", "name" };
		searchCtls.setReturningAttributes(returnedAtts);
		NamingEnumeration<SearchResult> answer = ldapCtx.search(searchBase, searchFilter, searchCtls);
		while (answer.hasMoreElements()) {
			SearchResult sr = answer.next();
			Attributes Attrs = sr.getAttributes();
			if (Attrs != null) {
				NamingEnumeration<?> ne = Attrs.getAll();
				while (ne.hasMore()) {
					Attribute Attr = (Attribute) ne.next();
					String name = Attr.getID();
					Enumeration<?> values = Attr.getAll();
					if (values != null) { // 迭代
						while (values.hasMoreElements()) {
							String value = "";
							if ("objectGUID".equals(name)) {
								value = UUID.nameUUIDFromBytes((byte[]) values.nextElement()).toString();
							} else {
								value = (String) values.nextElement();
							}
							System.out.println(name + " " + value);
						}
					}
				}
				System.out.println("=====================");
			}
		}

	}

}// end class LDAPDEMO
