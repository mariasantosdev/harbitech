<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/functions" prefix="fn" %>

<main class="container">
    <h2 class="subcategory__name">${subcategory.name}</h2>
    <div>
        <ul class="courses__list">
            <c:forEach items="${courses}" var="course">
                <li class="course-card">
                    <h3 class="course-card__name">${course.name}</h3>
                    <p class="course-card__hours">${course.completionTimeInHours}h</p>
                    <c:choose>
                        <c:when test="${fn:contains(enrolledCourseIds, course.id)}">
                            <p class="all-courses-finished-message">Curso finalizado!</p>
                        </c:when>
                        <c:otherwise>
                            <button class="course-card__finish-course"
                                    style="margin-block: 16px;background-color: #747c81;border:
                                                none;color: white;padding: 4px 6px;text-align: center;display: inline-block;
                                                font-size: 16px;cursor: pointer;"
                                    data-course-code="${course.codeUrl}"
                                    onclick="postRequest(this)">Finalizar curso
                            </button>
                        </c:otherwise>
                    </c:choose>
                </li>
            </c:forEach>
        </ul>
    </div>
</main>