<%@ page import="logic.generators.SVGUTil" %>
<html>
<body>
<h2>Hello World! Create a customer</h2>
<!-- rem, spær, stolper -->

<form method="get" action="employees">
    <input type="submit" value="see employees">
</form>
<h3>Login User</h3>
<form method="get" action="login">
    <input type="text" name="username" value="empTest" required>
    <input type="password" name="password" value="123" required>
    <input type="submit" value="login">
</form>
<h3>Create user</h3>
<form method="post" action="createCustomer">
    <input type="text" name="username" value="test" required>
    <input type="text" name="password" value="123" required>
    <input type="number" name="phone" value="213456" required>
    <input type="submit" value="create User">
</form>
<form method="get" action="getCustomer">
    <input type="text" name="username" value="testTwo">
    <input type="submit" value="findCustomer">
</form>
<h3>Create Order</h3>
<form method="post" action="createOrder">
    <input type="number" name="height" value="0" required>
    <input type="number" name="width" value="600" required>
    <input type="number" name="length" value="500" required>
    <input type="number" name="slope" value="45" required>
    <input type="text" name="orderee" value="customer" required>
    <input type="text" name="type" value="semihardwood" required>
    <input type="submit" value="send order">
</form>

<form method="post" action="employeeToOrder">
    <input type="number" name="employeeId" value="1" required>
    <input type="number" name="orderId" value="2" required>
    <input type="submit" value="added employeeToOrder">
</form>

</body>
</html>
