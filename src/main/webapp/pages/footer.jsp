<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ page isELIgnored="false" %>
<c:if test="${not empty sessionScope.locale}">
    <fmt:setLocale value="${sessionScope.locale}"/>
</c:if>
<fmt:setBundle basename="locale"/>

<fmt:message key="footer.contact" var="contact"/>
<footer class="footer">
    <div class="container">
        <div class="text-center p-4">
            <h6 style="color: lightgray" class="text-muted"> &copy; Fishing shop</h6>
            <p style="color: lightslategrey">${contact}: +375(29)5533509</p>
        </div>
    </div>
</footer>