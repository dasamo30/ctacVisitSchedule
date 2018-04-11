<%@page contentType="text/html" pageEncoding="UTF-8" session="true"%>
<%@taglib uri="http://www.springframework.org/tags" prefix="spring"%> 
<%@ taglib uri="http://www.springframework.org/tags/form" prefix="form"%>    
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt" %>
<jsp:useBean id="date" class="java.util.Date" /> 
<c:set var="contextPath" value="${pageContext.request.contextPath}"/>


<c:forEach items="${lisVisitSchedule}" var="visitSchedule">
<div id="divdataVisit" class='box-footer box-comments' style="display: block;">
              	
                  <div class='box-comment'>
                    <!-- User image -->
                    <img class='img-circle img' src='${contextPath}/images/icono_calendari_visita.png' alt='user image'>
                    <div class='comment-text'>
                      <span class="username">
                        <h4 class="box-title" style="margin-top: 5px;" >About visit data</h4>
                        <!-- <span class='text-muted pull-right'>8:03 PM Today</span> -->
                      </span>
             <div class="col-sm-4 invoice-col">
              Visitor
              <address>
                <strong id="full_name_visitor">${visitSchedule.full_name_visitor}</strong><br>
                Company: <span id="company_name">${visitSchedule.company_name}</span><br>
                ID: <span id="idNumber">${visitSchedule.number_license}</span><br>
                citizen ship: <span id="citizen_ship">${visitSchedule.citizen_ship}</span><br>
                Phone: <span id="phone_number">${visitSchedule.phone_number}</span><br>
                Email: <span id="email">${visitSchedule.email}</span>
              </address>
            </div><!-- /.col -->
            <div class="col-sm-4 invoice-col">
              Employe
              <address>
                <strong id="full_name_employee">${visitSchedule.full_name_employee}</strong><br>
                occupation: <span id="occupation_employee">${visitSchedule.occupation_employee}</span><br>
              </address>
            </div><!-- /.col -->
            <div class="col-sm-4 invoice-col">
            Visit
            <address>
              <b id="id_visit_schedule">${visitSchedule.call_cod} </b><br>
              
              <b>Status:</b> 
	              <c:choose>
			         <c:when test = "${visitSchedule.status eq 1}">
			            <span class="label label-primary" id="reasons_name">Generated</span><br>
			         </c:when>
			         <c:when test = "${visitSchedule.status eq 2}">
			            <span class="label label-info" id="reasons_name">on visit</span><br>
			         </c:when>
			         <c:when test = "${visitSchedule.status eq 3}">
			            <span class="label label-danger" id="reasons_name">finalized</span><br>
			         </c:when>
			         <c:when test = "${visitSchedule.status eq 4}">
			            <span class="label label-warning" id="reasons_name">Cancel</span><br>
			         </c:when>
			         <c:otherwise>
			            <span  id="reasons_name"> </span><br>
			         </c:otherwise>
		      	</c:choose>
              <b>Reason:</b> <span id="reasons_name">${visitSchedule.reasons_name}</span><br>
              <b>Date From:</b> <span id="date_hour"><fmt:formatDate pattern = "MM-dd-yyyy" value = "${visitSchedule.date_ini}"/></span><br>
              <b>Date To:</b> <span id="date_hour"><fmt:formatDate pattern = "MM-dd-yyyy" value = "${visitSchedule.date_end}"/></span><br>
              <b>Hour:</b> <span id="date_hour">${visitSchedule.hour}</span><br>
               <b>Department:</b> <span id="department_name">${visitSchedule.department_name}</span><br>
              <!--<b>Account:</b> 968-34567-->
              </address>
            </div>
<!--             <div class="col-sm-1 invoice-col"> -->
<!--             <address> -->
<!--               <br><br><br>	 -->
<%--               <b id="id_visit_schedule"><input type="checkbox" checked="checked" data-id="${visitSchedule.number_badge}"  data-cod="${visitSchedule.call_cod}"   class="ids" name="ids[]" value="${visitSchedule.id_visit_schedule}" ></b> --%>
<!--               </address> -->
<!--             </div> -->
            <!-- /.col -->
            </div><!-- /.comment-text -->
          </div><!-- /.box-comment -->
           <div class='box-comment'>
            <div class="row">
            <div class="col-xs-12 table-responsive">
                  <table class="table table-striped" id="tbRegister">
                  <thead>
                    <tr> 
                      <th></th>
                      <th>Badge</th>
                      <th>Date</th>
                      <th>Hour</th>
                    </tr>
                    </thead>
                    <tbody>
                    <c:forEach items="${visitSchedule.listVisitorLog}" var="visitorLog">
                    <tr class="text-light-blue">
                    <td><span class="label ${visitorLog.type eq 1 ?  'label-success': 'label-danger'}">${visitorLog.type eq 1 ?  'Ingreso': 'salida'}</span></td>
                      <td>${visitorLog.badge_number}</td>
                      <td><fmt:formatDate pattern = "MM-dd-yyyy" value = "${visitorLog.registration_date}"/></td>
                     <td><fmt:formatDate type = "time" value = "${visitorLog.registration_date}"/></td>
                    </tr>
                    </c:forEach>
<!--                     <tr class="text-light-blue"> -->
<!--                     <td><span class="label label-primary"> Salida </span></td> -->
<!--                       <td>232323-23-2323</td> -->
<!--                       <td>11-7-2014</td> -->
<!--                       <td>2:45</td> -->
<!--                     </tr> -->
                    </tbody>
                  </table>
            </div>
          </div>
                  </div>
                </div><!-- /.box-footer -->
 </c:forEach>              
               
            <div class="box-footer">
				<c:if test="${empty lisVisitSchedule}">                 

                 	<div id="msjSearchVisit" class="alert alert-warning alert-dismissable" >You do not have a scheduled visit for today</div>

                </c:if>
                </div><!-- /.box-footer -->
                <div id="editor"></div>
                