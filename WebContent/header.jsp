<jsp:useBean id="nyancat" scope="session" class="org.silix.the9ull.microbit.controlinterface.NyanCatJB" />
<%
for(int i=0 ; i<nyancat.getCount() ; i++) {
	//out.print("<img src=\"http://the9ull.silix.org/imageupload/4faad3fe/rainbow.png\" />");
	out.print(nyancat.getRainbow());
}
nyancat.increment();
%><jsp:getProperty name="nyancat" property="rainbow" /><jsp:getProperty name="nyancat" property="cat" /><br />
<jsp:getProperty name="nyancat" property="desc" /><br />
<br />
<a href=".">Home</a><br /><br />
