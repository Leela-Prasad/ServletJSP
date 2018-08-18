Using GET we can bookmark the url's as multiple request will result in similar result
POST Request cannot be bookmarked because the parameters will go as part of Http Headers so if we submit through html form then parameters will be passed as Http Headers, if we submit through browser then we will miss these parameters and code will not work
Service method can handle both GET and POST requests but
doGet will allow only GET requests
doPost will allow only POST requests

POST-REDIRECT-GET Design Pattern
================================
Most of the times POST Request is NOT a repetable action and if post request is again submitted by user(by refreshing the page)
then it may cause duplicate action in the system which is not desired.To avoid this situation we can use POST-REDIRECT-GET Design
pattern.
when user makes a post request then we will redirect to GET Request page so that user cannot submit multiple post requests.

Sessions and Cookies
====================
HTTP is a stateless protocol.
This is because it can't serve millions of users at a time if it was STATEFULL.
Generally if it statefull then the server will remember about the user that it was interacting, and maintaining
session with that user for a longer duration is resource intensive at the server side when it is serving large
number of customers, and there is a thresh-hold for the number of connections that a server can have.
because of these reasons HTTP is designed as a stateless protocol.
ssh is the example of statefull protocol.
Even though Http is stateless protocol we can make applications which seems to be stateful.
example : when we request cart details in amazon it will return with list of items in the cart.
How this is possible when http is stateless and amazon server don't know whom it is serving the request.
Below are the techniques used to make Http to look like stateful.
1. Cookies:
  when a user sent a request to server it will return the response and a cookie in a plain text, and this cookie is
  stored in users hard disk.
  cookie is something like a key value pair eg: user=user123
  when the response is served it will drop the connection to the server.
  when the user makes another request to the server this time server will read the cookie and serve the cart details
  based on the cookie. this time it will give the cart details along with updated cookie value. and now connection is dropped.
  like this it will repeat the process and HTTP looks like stateful protocol.

  Disadvantages of Cookies:
  =========================
  1. Cookies is written in a plain text and generally limited to max 4KB, so when we send a response which will have
  lot of objects stored inside cookie that may or may not fit into 4KB.
  2. Sometimes users will not allow cookies to store on their browser then our requests for maintaining sessions will not work.
  3. cookies are sent in a plain text not in binary format because antivirus may consider cookie as a virus.
  4. Writing code to process Cookie is relatively complex with the underlying APIs.

  2. Http Session:
  when a user made a request to the server for the first time then it will create Http Session Object on the server
  and return the response and "JSessionID" as cookie
  so here cookie contain only JSessionID=327846ihsfdkjnw3
  all the objects that are needed for the session are directly stored on the server for this JSessionID, so there
  is no restriction on the size of the objects that can be stored in a session unlike with plain cookie with 4KB.
  After serving the request connection to server will be dropped.
  if user made a request again to the server then the server will read the cookie from the browser(i.e., JSessionID in cookie)
  and serve the request accordingly.
  The below line will do all the things(i.e., reading cookie from the user if exists and serving the request else
  if will create empty session object on the server and send the cookie when the response is sent to user.)
  HttpSession session = request.getSession();

  If User disabled cookie then above approach will not work, for this we will use URL Rewriting
  In URL Rewriting JSessionID will be sent in the url itself instead of cookie.
  for this to happen we need to write
  String redirectUrl = "/thankYou.html"
  redirectUrl = response.encodeURL(redirectUrl);
  response.sendRedirect(redirectUrl)
  URL Rewriting will only happen when user is not allowing cookie on his browser
  If cookies are enabled JSessionID will be sent as a cookie.
  If cookies are not enabled then JSessionID will be sent in the url itself.

  Disadvantages of URL Rewriting:
  ===============================
  since JSessionID will be passed along with url, that url can be used in different browsers aslong as the
  session is active.

How to manage Abandon Sessions
==============================
sometimes users will not logout properly then the session objects still exist on the server,if this process
happen for large number of users then lot of session objects will be created on the server which will lead to
memory out of error and server will be down.
To avoid this we have to set session timeout in web.xml so that after specified amount of time session objects
will be destroyed.
<session-config>
  <session-timeout>2</session-timeout>
</session-config>
Here session timeout is in minutes. So after 2 minutes session will be destroyed. i.e., even though cookie has
JSessionID that will work and it is an expired JSessionID.
Default value for session-timeout is 0 which means it will never expire.
when we configure session timeout for a new value then the existing sessions will follow old session timeout and the sessions which are created after new timeout value will follow new timeout value, so we need to check and terminate sessions manually if not required form the manager console.

When Tomcat shutdown/Restarts then what will happen to the existing sessions?
Sessions will be written to a file so that sessions will still be active as long as the session timeout happens.
Sessions aren't destroyed until the session times out. If you shut the server
down, existing sessions will be written to file. If you bring the server back
up before the timeout of those sessions, they will still exist upon server
restart. If you think about it, this is usually desired behavior. I believe
you can turn this off on the Coyote connector,

Servlet Security:
=================
Servlet security has ability for authorization

Authorization:
here we can define roles and map these roles are mapped to resources that exist in the web applicaiton.

Defining Roles:
<!-- Listing Roles that exist
		 in the system.This is generally
		 not required in tomcat but needed in
		 other webservers.
	-->
	 <security-role>
		<role-name>user</role-name>
	</security-role>

	<security-role>
		<role-name>admin</role-name>
	</security-role>


Mapping Roles to specific Resources:
<!-- authorization
		This security-constraint tag will map the
		resources to a specific role in the system.
		example:
		/order.html and /thankYou.html
		is mapped to user role,so any one login with
		others roles cannot access above 2 resources.
	-->
	<security-constraint>
		<web-resource-collection>
			<web-resource-name>orders page</web-resource-name>
			<url-pattern>/order.html</url-pattern>
			<url-pattern>/thankYou.html</url-pattern>
		</web-resource-collection>

		<auth-constraint>
			<role-name>user</role-name>
		</auth-constraint>
	</security-constraint>

	<security-constraint>
		<web-resource-collection>
			<web-resource-name>staff pages</web-resource-name>
			<url-pattern>/admin/*</url-pattern>
		</web-resource-collection>

		<auth-constraint>
			<role-name>admin</role-name>
		</auth-constraint>
	</security-constraint>
	<!-- authorization ends -->

Here we mapped user role to order.html and thankYou.html resources.

Security security is all about role based. It doesn't have granular level of specifying security like
these users can control these resources.

Authentication:
Authentication is not part of servlet specification but we can configure this in tomcat-users.xml file
meaning we can list users with username , password and there role.

<role rolename="user"/>
<role rolename="admin"/>
<user username="leela" password="pass" roles="user"/>

Login Page:
For Login page we can two types.
1. BASIC
2. FORM

1. BASIC will provide a default login page as a popup.
<login-config>
		<auth-method>BASIC</auth-method>
</login-config>

2. FORM will allow us to specify customized login page.
<login-config>
		<auth-method>FORM</auth-method>
		<form-login-config>
			<form-login-page>/login.html</form-login-page>
			<form-error-page>/login-failed.html</form-error-page>
		</form-login-config>
</login-config>

But login html should have these fields as is, else Authentication will not work.
FORM action="j_security_check"
username = "j_username"
password = "j_password"

<form action="j_security_check" method="POST">
	Username : <input type="text" name="j_username" />
	Password : <input type="password" name="j_password" />
	<input type="submit" value="login" />
</form>

Filters:
========
Filters can intercept the Http Request before servlet processing.
we can do this interception by providing the url pattern same as servlet url, thus filter can have array of
url patterns to intercept request for multiple servlets.
we can intercept all Http Requests by providing url pattern as "/"

Filter will have 3 abstract methods.
1. init() - just like constructor.
2. doFilter() - interception logic.
3. destroy() - this will be invoked when all the threads are completed and unloaded the filter from the method area.
  But this unloading cannot be guaranteed, so it is better not put any code here as it is unpredictable this is
  similar to finalize() method in GC.

at the last step/first step of doFilter() method we need to write below line so that it will be given to the
next filter in the chain and at last it will be given to the servlet.
chain.doFilter(request,response);
multiple filters can be there for a single Servlet.

Java Server Pages(JSP):
=======================
In most web based projects we have to separate the view logic from business logic.
This View logic is written in JSP, it will only know how to present data that is obtained from business logic
in a nice way.
Internally JSPs are converted into servlets.
when the client access JSP, tomcat converts that into servlet and compiles it into binary and this class file
is sent to client.
when the client access same JSP then tomcat will check whether there is any change in the JSP content,
if there is a change then it will again convert JSP to servlet and compile it to binary
else it will return same compiler jsp,
that is the reason why we will see a small lag in serving JSP page for the first request.

While converting JSP to Servlet it will place code inside scriptlet tag (<% %>) in servlet service/doGet/doPost method.
this is the reason why import statements cannot go in scriptlet tag, because these imports will be placed in service method.
so we need instruct JSP Translator that it a directive meaning(<%@ %>) it has to compiled in a different part of the file
<%@ page %>
this will tell it is a page directive meaning all this lines will go to the top of the page.

Expression tag:
Any Variables can be represesnted using expression tag.
<%= varName %>

Include Directive:
Any common content can be included using include directive.
<jsp:include page="common.jsp" /> (or)
<%@ include %>


JSTL(Java Standard Tag Library)
====
***In Jsp, if we use JSTL below lines are same
  ${total} is same as request.getAttribute("total")

AJAX(Asynchronous Javascript and XML)
====
In AJAX it will request the web server for a page and the web server will return page along with Javascript component.
This Javascript component contains 2 parts.
One part will request the web server in the background for a specified period of time or when a user clicks a
particular button.
Another part will receive the response returned by the web server for the part1 request and updates the webpage.

when a client sends a request to server, then server will send resource file(i.e., html/jsp page) along with javascript.
AJAX has 3 steps.
1. when a user clicks a button or a specified amount of time is elapsed then javascript which is sent in the resource will make a HTTP Request to the server Asynchronously i.e., in the background.
2. Now there should be a process i.e., a servlet to process this HTTP Request and will send response back to the browser.
3. There is another Javascript code in the origingal response that can replace this response in the specified fields, so that only a part of the page is loaded not the entire page.

Asynchronous Servlets:
======================
Generally, web server an upper limit on how many can users requests it can process concurrently.
That is defined as max number of threads that it can allowed to create. These threads are created in thread pool.
These threads are called Http Worker Thread.
Asynchronous Servlets is all about spanning a thread and processing it in the background, and this thread is not
from the Http Worker Thread.
If there is any process which will run for a long period then it will block that particular Http Worker Thread.
If we create an Asynchronous servlet then this long running process is span into another background thread and
the HttpWorkerThread will complete the remaining process and that HttpWorkerThread will be placed in the thread pool
so that it can server another user requests. where as that long running process is still running in the background.
In this way we can handle large number of users.
Disadvantage of Asynchronous Servlet is in SOME servers(eg: Tomcat) this spanned thread is again created from
Http Worker Thread where we loose whole advantage, so this method is not widely encouraged.
we can overcome this problem with WebSockets.
