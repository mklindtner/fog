<%@ page import="entities.userEntities.Employee" %>
<%@ page import="entities.OrderEntities.Order" %>
<%@ page import="java.util.List" %>
<%@ page import="java.util.ArrayList" %>

<%--
  Created by IntelliJ IDEA.
  User: mkl
  Date: 4/26/18
  Time: 12:43 PM
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <meta charset="UTF-8">
    <title>Title</title>
    <link rel="stylesheet" href="https://maxcdn.bootstrapcdn.com/bootstrap/3.3.7/css/bootstrap.min.css">
    <link rel="stylesheet" href="css/login.css">
    <script src="https://ajax.googleapis.com/ajax/libs/jquery/1.12.4/jquery.min.js"></script>
    <script src="https://maxcdn.bootstrapcdn.com/bootstrap/3.3.7/js/bootstrap.min.js"></script>
    <script src="css/orderList.css"></script>
</head>
<body>
<%@ include file="/WEB-INF/customer/customerHeader.jsp"%>
<div class="container">
    <ul class="nav nav-tabs">
        <li role="presentation" class="active">
            <a href="redirect?goToPage=customerOrders&role=customer">Your Orders</a>
        </li>
        <li role="presentation">
            <a href="redirect?goToPage=customerHomepage&role=customer">Create Order </a>
        </li>
    </ul>

    <div class="bd-example">
        <table class="table">
            <thead class="thead-dark">
            <tr>
                <th scope="col">#</th>
                <th scope="col">Height</th>
                <th scope="col">Width</th>
                <th scope="col">Length</th>
            </tr>
            <tr>
                <th scope="colgroup">PENDING</th>
            </tr>
            </thead>
            <tbody>
            <%
                List customerOrders = (List) request.getSession().getAttribute("customerOrders");
                List pending = new ArrayList();
                List offer = new ArrayList();
                List accepted = new ArrayList();
                List send = new ArrayList();
                for (int i = 0; i < customerOrders.size(); i++) {
                    Order order = (Order) customerOrders.get(i);
                    if (order.getStatus() == Order.Status.PENDING)
                        pending.add(order);
                    if (order.getStatus() == Order.Status.OFFER)
                        offer.add(order);
                    if (order.getStatus() == Order.Status.ACCEPTED)
                        accepted.add(order);
                    if (order.getStatus() == Order.Status.SEND)
                        send.add(order);
                }
                String pendingIndexString = request.getParameter("pendingIndex");
                String offerIndexString = request.getParameter("offerIndex");
                String acceptedIndexString = request.getParameter("acceptedIndex");
                String sendIndexString = request.getParameter("sendIndex");
                int pendingIndex = 0, offerIndex = 0, acceptedIndex = 0, sendIndex = 0;
                if (pendingIndexString != null)
                    pendingIndex = Integer.parseInt(pendingIndexString);
                if (offerIndexString != null)
                    offerIndex = Integer.parseInt(offerIndexString);
                if (acceptedIndexString != null)
                    acceptedIndex = Integer.parseInt(acceptedIndexString);
                if (sendIndexString != null)
                    sendIndex = Integer.parseInt(sendIndexString);


                for (int i = pendingIndex; i < pendingIndex + 5 && i < pending.size(); i++) {
                    Order order = (Order) pending.get(i);
            %>
            <tr>
                <th scope="row"><%=order.getId()%>
                </th>
                <td><%=order.getHeight()%>
                </td>
                <td><%=order.getWidth()%>
                </td>
                <td><%=order.getLength()%>
                </td>
            </tr>
            <%
                }
            %>
            <% if (pending.size() > 5) { %>
            <tr>
                <td colspan="4">
                    <nav aria-label="Page navigation example">
                        <ul class="pagination">
                            <%for (int i = 0; i < pending.size() / 5 + 1; i++) { %>
                            <li class="page-item">
                                <a class="page-link"
                                   href="redirect?goToPage=customerOrders&role=customer&pendingIndex=<%=(i * 5) + 1%>">side<%=i + 1%>
                                </a>
                            </li>
                            <% }%>
                        </ul>
                    </nav>
                </td>
            </tr>
            <% } %>
            <tr>
                <th scope="colgroup">Offers from FOG</th>
            </tr>
            <%
                for (int i = offerIndex; i < offerIndex + 5 && i < offer.size(); i++) {
                    Order order = (Order) offer.get(i);
            %>
            <tr>
                <th scope="row"><%=order.getId()%>
                </th>
                <td><%=order.getHeight()%>
                </td>
                <td><%=order.getWidth()%>
                </td>
                <td><%=order.getLength()%>
                </td>
                <td>
                    <form method="post" action="customerAcceptOrder">
                        <input type="hidden" name="orderId" value="<%=order.getId()%>">
                        <button class="btn btn-primary" type="submit">Accept Offer</button>
                    </form>
                </td>
            </tr>
            <%
                }
            %>
            <% if (offer.size() > 5) { %>
            <tr>
                <td colspan="4">
                    <nav aria-label="Page navigation example">
                        <ul class="pagination">
                            <%for (int i = 0; i < offer.size() / 5 + 1; i++) { %>
                            <li class="page-item">
                                <a class="page-link"
                                   href="redirect?goToPage=customerOrders&role=customer&offerIndex=<%=(i * 5) + 1%>">side<%=i + 1%>
                                </a>
                            </li>
                            <% }%>
                        </ul>
                    </nav>
                </td>
            </tr>
            <% } %>
            <tr>
                <th scope="colgroup">Orders Accepted</th>
            </tr>
            <%
                for (int i = acceptedIndex; i < acceptedIndex + 5 && i < accepted.size(); i++) {
                    Order order = (Order) customerOrders.get(i);
            %>
            <th scope="row"><%=order.getId()%>
            </th>
            <td><%=order.getHeight()%>
            </td>
            <td><%=order.getWidth()%>
            </td>
            <td><%=order.getLength()%>
            <td>
                <a class="button btn btn-primary" data-toggle="modal" data-target="#myModal<%=order.getId()%>">Order
                    Information</a>
                <div class="modal fade" id="myModal<%=order.getId()%>" role="dialog">
                    <div class="modal-dialog">

                        <!-- Modal content-->
                        <div class="modal-content">
                            <div class="modal-header">
                                <button type="button" class="close" data-dismiss="modal">&times;</button>
                                <h4 class="modal-title">Order Information</h4>
                            </div>
                            <div class="col-md-8 col-md-offset-0 modal-body">
                                <div class="form-group col-md-4">
                                    <form method="get" action="generateCarport">
                                        <input type="hidden" name="orderId" value="<%=order.getId()%>">
                                        <button class="btn btn-primary" type="submit">see 2D carport</button>
                                    </form>
                                </div>
                                <div class="form-group col-md-4">
                                    <form method="get" action="billOfMaterial">
                                        <input type="hidden" name="orderId" value="<%=order.getId()%>">
                                        <button class="btn btn-primary" type="submit">see OrderList carport</button>
                                    </form>
                                </div>
                            </div>
                            <div class="modal-footer">
                                <button type="button" class="btn btn-default" data-dismiss="modal">Close</button>
                            </div>
                        </div>
                    </div>
                </div>
            </td>
            </td>
            </tbody>
            <%
                }
            %>
            <% if (accepted.size() > 5) { %>
            <tr>
                <td colspan="4">
                    <nav aria-label="Page navigation example">
                        <ul class="pagination">
                            <%for (int i = 0; i < accepted.size() / 5 + 1; i++) { %>
                            <li class="page-item">
                                <a class="page-link"
                                   href="redirect?goToPage=customerOrders&role=customer&acceptedIndex=<%=(i * 5) + 1%>">side<%=i + 1%>
                                </a>
                            </li>
                            <% }%>
                        </ul>
                    </nav>
                </td>
            </tr>
            <% } %>
            <tr>
                <th scope="colgroup">Orders on the way</th>
            </tr>
            <%
                for (int i = sendIndex; i < sendIndex + 5 && i < send.size(); i++) {
                    Order order = (Order) send.get(i);
            %>
            <th scope="row"><%=order.getId()%>
            </th>
            <td><%=order.getHeight()%>
            </td>
            <td><%=order.getWidth()%>
            </td>
            <td><%=order.getLength()%>
            </td>
            <td>
                <a class="button btn btn-primary" data-toggle="modal" data-target="#myModal<%=order.getId()%>">Order
                    Information</a>
                <div class="modal fade" id="myModal<%=order.getId()%>" role="dialog">
                    <div class="modal-dialog">

                        <!-- Modal content-->
                        <div class="modal-content">
                            <div class="modal-header">
                                <button type="button" class="close" data-dismiss="modal">&times;</button>
                                <h4 class="modal-title">Order Information</h4>
                            </div>
                            <div class="col-md-8 col-md-offset-0 modal-body">
                                <div class="form-group col-md-4">
                                    <form method="get" action="generateCarport">
                                        <input type="hidden" name="orderId" value="<%=order.getId()%>">
                                        <button class="btn btn-primary" type="submit">see 2D carport</button>
                                    </form>
                                </div>
                                <div class="form-group col-md-4">
                                    <form method="get" action="billOfMaterial">
                                        <input type="hidden" name="orderId" value="<%=order.getId()%>">
                                        <button class="btn btn-primary" type="submit">see OrderList carport</button>
                                    </form>
                                </div>
                            </div>
                            <div class="modal-footer">
                                <button type="button" class="btn btn-default" data-dismiss="modal">Close</button>
                            </div>
                        </div>
                    </div>
                </div>
            </td>
            </tbody>
            <%
                }
            %>
            <% if (send.size() > 5) { %>
            <tr>
                <td colspan="4">
                    <nav aria-label="Page navigation example">
                        <ul class="pagination">
                            <%for (int i = 0; i < send.size() / 5 + 1; i++) { %>
                            <li class="page-item">
                                <a class="page-link"
                                   href="redirect?goToPage=customerOrders&role=customer&sendIndex=<%=(i * 5) + 1%>">side<%=i + 1%>
                                </a>
                            </li>
                            <% }%>
                        </ul>
                    </nav>
                </td>
            </tr>
            <% } %>
        </table>
    </div>
</div>
</body>
</html>
