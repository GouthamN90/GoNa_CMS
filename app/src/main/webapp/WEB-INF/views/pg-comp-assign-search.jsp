<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="tag" tagdir="/WEB-INF/tags" %>

<c:choose>
    <c:when test="${not empty listComponents}">
        <div class="component-listing">
         <c:forEach var="item" items="${listComponents}">
             <tag:cmscomponent-card template="${item.template}" uid="${item.uid}" name="${item.name}" page ="AssignPage" 
             imageUrl="${item.previewImgUrl}" itemId="${item.id}" version="${item.version}"	status="${item.approvalStatus}"/>
         </c:forEach>
        </div>
    </c:when>
    
    <c:otherwise>
        <tag:empty-list-tag />
    </c:otherwise>
</c:choose>