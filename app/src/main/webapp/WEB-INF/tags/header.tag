<%@ tag language="java" pageEncoding="UTF-8" description="GoNa Backoffice Common Header Component" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ attribute name="username" type="java.lang.String" required="false" rtexprvalue="true" description="Current logged-in user display name" %>

<link rel="stylesheet" type="text/css" href="<c:url value='/css/header.css'/>"/>
<script defer src="<c:url value='/js/header.js' />"></script>

<!-- GoNa Backoffice Common Header Component -->
<header class="gona-header">
    <div class="header-left">
        <a href="<c:url value='/' />" class="logo-link">
            <img src="<c:url value='/images/logo.png' />" 
                 alt="GoNa Logo" 
                 class="header-logo" />
        </a>
        <span class="header-title">GoNa Backoffice</span>
    </div>
    
    <div class="header-right">
        <div class="user-profile-menu" id="userMenu">
            <button class="menu-trigger" onclick="toggleDropdown(event)" aria-haspopup="true" aria-expanded="false">
                <!-- User Profile SVG Avatar Icon -->
                <svg class="avatar-icon" viewBox="0 0 24 24" fill="none" stroke="currentColor" stroke-width="2" stroke-linecap="round" stroke-linejoin="round">
                    <path d="M20 21v-2a4 4 0 0 0-4-4H8a4 4 0 0 0-4 4v2" />
                    <circle cx="12" cy="7" r="4" />
                </svg>
                <span class="user-display-name">
                    <c:choose>
                        <c:when test="${not empty username}">
                            <c:out value="${username}" />
                        </c:when>
                        <c:otherwise>Backoffice User</c:otherwise>
                    </c:choose>
                </span>
                <!-- Down Arrow Indicator -->
                <svg class="dropdown-chevron" viewBox="0 0 24 24" fill="none" stroke="currentColor" stroke-width="2" stroke-linecap="round" stroke-linejoin="round">
                    <polyline points="6 9 12 15 18 9" />
                </svg>
            </button>
            
            <div class="dropdown-content" id="dropdownMenu">
                <div class="dropdown-header">
                    <span class="user-role">Access Authorization</span>
                    <span class="user-name-display">
                        <c:choose>
                            <c:when test="${not empty username}">
                                <c:out value="${username}" />
                            </c:when>
                            <c:otherwise>Administrator</c:otherwise>
                        </c:choose>
                    </span>
                </div>
                <div class="dropdown-divider"></div>
                
                <!-- Navigation Items -->
                <a href="<c:url value='/profile' />" class="dropdown-item">
                    <svg class="item-icon" viewBox="0 0 24 24" fill="none" stroke="currentColor" stroke-width="2" stroke-linecap="round" stroke-linejoin="round">
                        <path d="M20 21v-2a4 4 0 0 0-4-4H8a4 4 0 0 0-4 4v2"/><circle cx="12" cy="7" r="4"/>
                    </svg>
                    My Settings
                </a>
                <a href="<c:url value='/admin/security' />" class="dropdown-item">
                    <svg class="item-icon" viewBox="0 0 24 24" fill="none" stroke="currentColor" stroke-width="2" stroke-linecap="round" stroke-linejoin="round">
                        <rect x="3" y="11" width="18" height="11" rx="2" ry="2"/><path d="M7 11V7a5 5 0 0 1 10 0v4"/>
                    </svg>
                    Security Settings
                </a>
                
                <div class="dropdown-divider"></div>
                
                <a href="<c:url value='/logout' />" class="dropdown-item signout-item">
                    <svg class="item-icon" viewBox="0 0 24 24" fill="none" stroke="currentColor" stroke-width="2" stroke-linecap="round" stroke-linejoin="round">
                        <path d="M9 21H5a2 2 0 0 1-2-2V5a2 2 0 0 1 2-2h4"/><polyline points="16 17 21 12 16 7"/><line x1="21" y1="12" x2="9" y2="12"/>
                    </svg>
                    Sign Out
                </a>
            </div>
        </div>
    </div>
</header>