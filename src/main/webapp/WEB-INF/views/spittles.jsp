<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>

<c:forEach items="${spittleList}" var="spittle">
    <li id="spittle_<c:out value="${spittle.id}"/>">
        <div class="spittleMessage">
            <c:out value="${spittle.message}"/>
        </div>
        <div>
            <span class="spittleTimestamp"><c:out value="${spittle.timestamp}"/></span>
            <span class="spittleLocation">
                (<c:out value="${spittle.latitude}"/>, <c:out value="${spittle.longitude}"/>)
            </span>
        </div>
    </li>
</c:forEach>