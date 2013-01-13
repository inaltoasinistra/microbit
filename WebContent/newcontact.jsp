<form name="login" action="index.jsp?contacts&newContact" method="POST">
	<input type="text" name="alias" value="new alias" onclick="this.form.elements[0].value = ''" />
	<input type="text" name="contactAddress" value="address or user id" onclick="this.form.elements[1].value = ''" />
	<input type="submit" value="Go" style="visibility:hidden" />
</form>
