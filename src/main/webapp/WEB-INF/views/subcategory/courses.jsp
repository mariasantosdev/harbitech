<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/functions" prefix="fn" %>

<main class="container">
    <h2 class="subcategory__name">${subcategory.name}</h2>
    <div class="subcategory"
         data-courses-count="${fn:length(subcategory.getActiveCourses())}"
         data-subcategory-code="${subcategory.codeUrl}">
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
    <button id="load-next-steps" class="load-next-steps" onclick="loadNextSteps()"
            style="margin-top: 20px;background-color: #4CAF50;border: none;color: white;padding: 10px 20px;text-align:
                    center;display: ${allCoursesCompleted ? "block" : "none"};font-size: 16px;cursor: pointer;">
        Carregar próximos passos da jornada
    </button>

    <div id="congratulations-message" style="display: none; margin-top: 20px; font-size: 16px;">
        Parabéns, sua jornada do herói dentro dessa categoria foi concluída com sucesso!
        Mas não se esqueça de que a jornada pelo conhecimento nunca termina. Para explorar outras categorias,
        <a href="/onboarding/study-mode">clique aqui</a>.
    </div>
</main>