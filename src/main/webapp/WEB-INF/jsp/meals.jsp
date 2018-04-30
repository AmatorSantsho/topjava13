<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags" %>
<%@ taglib prefix="fn" uri="http://topjava.javawebinar.ru/functions" %>
<html>
<jsp:include page="fragments/headTag.jsp"/>
<body>
<script type="text/javascript" src="resources/js/datatablesUtil.js" defer></script>
<script type="text/javascript" src="resources/js/mealDatatables.js" defer></script>
<jsp:include page="fragments/bodyHeader.jsp"/>

<div class="jumbotron">
    <div class="container">
        <section>
            <h3><spring:message code="meal.title"/></h3>
<br>
            <%--<form method="post" action="meals/filter">--%>
            <div class="container">
                <div class="row">
                    <div class="col-xs-12 col-sm-4">
                        <form id="filterForm">
                            <%--<dl>--%>
                            <div class="form-group row">
                                <label for="startDate" class="col-sm-4 col-form-label"><spring:message
                                        code="meal.startDate"/></label>

                                <div class="col-sm-8">
                                    <input class="form-control" type="date" id="startDate" name="startDate"
                                           value="${param.startDate}">
                                </div>
                            </div>

                            <div class="form-group row">
                                <label for="endtDate" class="col-sm-4 col-form-label"><spring:message
                                        code="meal.endDate"/></label>

                                <div class="col-sm-8">
                                    <input class="form-control" type="date" id="endtDate" name="endDate"
                                           value="${param.endDate}">
                                </div>
                            </div>

                            <div class="form-group row">
                                <label for="startTime" class="col-sm-4 col-form-label"><spring:message
                                        code="meal.startTime"/></label>

                                <div class="col-sm-8">
                                    <input class="form-control" type="time" id="startTime" name="startTime"
                                           value="${param.startTime}">
                                </div>
                            </div>

                            <div class="form-group row">
                                <label for="endTime" class="col-sm-4 col-form-label"><spring:message
                                        code="meal.endTime"/></label>

                                <div class="col-sm-8">
                                    <input class="form-control" type="time" id="endTime" name="endTime"
                                           value="${param.endTime}">
                                </div>
                            </div>
                            <button class="btn btn-primary" type="button" onclick="filter()"><spring:message
                                    code="meal.filter"/></button>
                            <button class="btn btn-primary" type="button" onclick="reset()"><spring:message
                                    code="meal.reset"/></button>
                        </form>
                    </div>
                    <div class="col-xs-12 col-sm-8"></div>
                </div>
            </div>
            <hr>
            <button class="btn btn-primary" onclick="add()">
                <span class="fa fa-plus"></span>
                <spring:message code="meal.add"/>
            </button>
            <hr>
            <table class="table table-striped" id="datatable">
                <thead>
                <tr>
                    <th><spring:message code="meal.dateTime"/></th>
                    <th><spring:message code="meal.description"/></th>
                    <th><spring:message code="meal.calories"/></th>
                    <th></th>
                    <th></th>
                </tr>
                </thead>
                <c:forEach items="${meals}" var="meal">
                    <jsp:useBean id="meal" scope="page" type="ru.javawebinar.topjava.to.MealWithExceed"/>
                    <tr data-mealExceed="${meal.exceed}">
                        <td>
                                <%--${meal.dateTime.toLocalDate()} ${meal.dateTime.toLocalTime()}--%>
                                <%--<%=TimeUtil.toString(meal.getDateTime())%>--%>
                                <%--${fn:replace(meal.dateTime, 'T', ' ')}--%>
                                ${fn:formatDateTime(meal.dateTime)}
                        </td>
                        <td>${meal.description}</td>
                        <td>${meal.calories}</td>
                        <td><a><span class="fa fa-pencil"></span></a></td>
                        <td><a onclick="deleteRow(${meal.id})"><span class="fa fa-remove"></span></a></td>
                            <%--<td><a href="meals/update?id=${meal.id}"><spring:message code="common.update"/></a></td>--%>
                            <%--<td><a href="meals/delete?id=${meal.id}"><spring:message code="common.delete"/></a></td>--%>
                    </tr>
                </c:forEach>
            </table>
        </section>
    </div>
</div>
<div class="modal fade" tabindex="-1" id="editRow">
    <div class="modal-dialog">
        <div class="modal-content">
            <div class="modal-header">
                <h4 class="modal-title"><spring:message code="meal.add"/></h4>
                <button type="button" class="close" data-dismiss="modal">&times;</button>
            </div>
            <div class="modal-body">
                <form id="detailsForm">
                    <input type="hidden" id="id" name="id">

                    <div class="form-group">
                        <label for="dateTime" class="col-form-label"><spring:message code="meal.dateTime"/></label>
                        <input type="text" class="form-control" id="dateTime" name="dateTime"
                               placeholder="<spring:message code="meal.dateTime"/>">
                    </div>

                    <div class="form-group">
                        <label for="description" class="col-form-label"><spring:message
                                code="meal.description"/></label>
                        <input type="text" class="form-control" id="description" name="description"
                               placeholder="<spring:message code="meal.description"/>">
                    </div>

                    <div class="form-group">
                        <label for="calories" class="col-form-label"><spring:message code="meal.calories"/></label>
                        <input type="password" class="form-control" id="calories" name="calories"
                               placeholder="<spring:message code="meal.calories"/>">
                    </div>
                </form>
            </div>
            <div class="modal-footer">
                <button type="button" class="btn btn-secondary" data-dismiss="modal">
                    <span class="fa fa-close" aria-hidden="true"></span>
                    <spring:message code="common.cancel"/>
                </button>
                <button type="button" class="btn btn-primary" onclick="save()">
                    <span class="fa fa-check" aria-hidden="true"></span>
                    <spring:message code="common.save"/>
                </button>
            </div>
        </div>
    </div>
</div>

<jsp:include page="fragments/footer.jsp"/>
</body>
</html>