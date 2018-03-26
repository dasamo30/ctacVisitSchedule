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
            <div class="col-sm-3 invoice-col">
            Visit
            <address>
              <b id="id_visit_schedule">${visitSchedule.call_cod} </b><br>
              <b>Reason:</b> <span id="reasons_name">${visitSchedule.reasons_name}</span><br>
              <b>Date From:</b><span id="date_hour">${visitSchedule.date_ini}</span><br>
              <b>Date To:</b><span id="date_hour">${visitSchedule.date_end}</span><br>
              <b>Hour:</b><span id="date_hour">${visitSchedule.hour}</span><br>
               <b>Department:</b><span id="department_name">${visitSchedule.department_name}</span><br>
              <!--<b>Account:</b> 968-34567-->
              </address>
            </div>
            <div class="col-sm-1 invoice-col">
            <address>
              <br><br><br>	
              <b id="id_visit_schedule"><input type="checkbox" checked="checked"  class="ids" name="ids[]" value="${visitSchedule.id_visit_schedule}" ></b>
              </address>
            </div>
            <!-- /.col -->
            </div><!-- /.comment-text -->
          </div><!-- /.box-comment -->
<!--            <div class='box-comment'> -->
<!--             <div class="row"> -->
<!--             <div class="col-xs-12 table-responsive"> -->
<!--                   <table class="table table-striped" id="tbRegister" style="display:block;"> -->
<!--                   <thead> -->
<!--                     <tr>  -->
<!--                       <th></th> -->
<!--                       <th>Badge</th> -->
<!--                       <th>Date</th> -->
<!--                       <th>Hour</th> -->
<!--                     </tr> -->
<!--                     </thead> -->
<!--                     <tbody> -->
<!--                     <tr class="text-light-blue"> -->
<!--                     <td><span class="label label-success">Ingreso</span></td> -->
<!--                       <td>232323-23-2323</td> -->
<!--                       <td>11-7-2014</td> -->
<!--                      <td>2:45</td> -->
<!--                     </tr> -->
<!--                     <tr class="text-light-blue"> -->
<!--                     <td><span class="label label-primary"> Salida </span></td> -->
<!--                       <td>232323-23-2323</td> -->
<!--                       <td>11-7-2014</td> -->
<!--                       <td>2:45</td> -->
<!--                     </tr> -->
<!--                     </tbody> -->
<!--                   </table> -->
<!--             </div> -->
<!--           </div> -->
<!--                   </div> -->
                </div><!-- /.box-footer -->
 </c:forEach>              
               
                             <div class="box-footer">
                <form id="frmRegisterVisit" role="form" >
                    <section class="col-md-12 col-print-10">                          
                    <div class="form-group form-horizontal col-md-4">
                      <label for="inputEmail3" class="col-sm-2 control-label" style="width: auto;">
                       <input type="checkbox" class="flat-red" checked>
                      Badge
                      </label>
                      <div class="input-group input-group-sm">
                        <input type="text" class="form-control" id="badge_number" name="badge_number" placeholder="Badge" required >
                      </div>
                    </div>
                    <div class="form-group form-horizontal col-md-3">
                      <label for="inputEmail3" class="col-sm-2 control-label" style="width: auto;">
                      Reason
                      </label>
                      <div class="input-group input-group-sm">
                        <select id="cboReason" name="reason" class="form-control">
                        <option></option>
                        <option value="1">Ingreso Temporal</option>
                        <option value="2">Perdidad de Badge</option>
                        </select>
                      </div>
                    </div>
                    <div class="form-group form-horizontal col-md-3">
                      <label for="inputEmail3" class="col-sm-2 control-label" style="width: auto;">
                      type
                      </label>
                      <div class="input-group input-group-sm">
                        <select id="id_type" name="id_type" class="form-control" required>
                        <option></option>
                        <option value="1">Ingreso</option>
                        <option value="2">Salida</option>
                        </select>
                      </div>
                    </div>
                    <button id="btnregiter" type="submit" class="btn btn-success pull-right"><i class="fa  fa-sign-in"></i> Register Visit</button>  
	                </section>
	                
                     </form>
                 <div id="msjSearchVisit" ></div>
                </div><!-- /.box-footer -->
                