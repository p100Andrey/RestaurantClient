<%@ page import="java.util.ArrayList" %>
<%@ page import="java.util.List" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="C" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>
<%
    // Create an ArrayList with test data
    ArrayList<String> list = new ArrayList();
    list.add("Zakuski");
    list.add("Pervie Blyuda");
    list.add("Vtorie Blyuda");
    list.add("Desert");
    pageContext.setAttribute("razdely", list);
%>

<html>

<head>
    <title>Main page</title>
</head>

<style>
    a {
        text-decoration: none;
    }

    /* Убираем подчеркивание у ссылок */
    a:hover {
        text-decoration: underline;
    }

    /* Добавляем подчеркивание при наведении курсора мыши на ссылку */
</style>

<body>
<h1 style="background-color:#ffffff; font-family: 'Franklin Gothic Medium'; color:#0cc316; height:20px"><big><big>&emsp;ELF</big></big>
</h1>
<table bordercolor="white" border="10" cellpadding="5">
    <tr>
        <td>
            <h1>Restaurant: Kiev, str. Reitarska 5, t. 808880888 </h1>
            <hr width="80%" align="left">
            <form METHOD="POST">
                <table>
                    <tr>
                        <td><input TYPE="submit" name="mainpage" value="Main Page"
                                   style="background-color:#40c373;width:150px;height:20px"></td>
                        <td><input TYPE="submit" name="restaurantmap" value="Restaurant Map"
                                   style="background-color:#40c373;width:150px;height:20px"></td>
                        <td><input TYPE="submit" name="restaurantstaff" value="Restaurant Staff"
                                   style="background-color:#40c373;width:150px;height:20px"></td>
                        <td><input TYPE="submit" name="contacts" value="Contacts"
                                   style="background-color:#40c373;width:150px;height:20px"></td>
                    </tr>
                </table>
            </form>
            <table>

                <tr><h3>&emsp;&emsp;&emsp;Meny</h3></tr>

                <tr><h4>&emsp;Zakuski</h4></tr>
                <c:forEach var="blydo" items="${lb}" varStatus="status">
                    <c:if test="${razdely.get(0).equals(blydo.getKategoriya())}">
                        <tr>
                            <td><a href="mainPage?blydoname=${blydo.getNazvanie()}"
                                   name="blydoname">&emsp;${blydo.getNazvanie()}</a></td>
                            <td>&emsp;- weight - ${blydo.getVes()}</td>
                            <td>- prise - ${blydo.getStoimost()};</td>
                        </tr>
                    </c:if>
                </c:forEach>
            </table>

            <table>
                <br/>
                <tr><h4>&emsp;Pervie Blyuda</h4></tr>
                <c:forEach var="blydo" items="${lb}" varStatus="status">
                    <c:if test="${razdely.get(1).equals(blydo.getKategoriya())}">
                        <tr>
                            <td><a href="mainPage?blydoname=${blydo.getNazvanie()}"
                                   name="blydoname">&emsp;${blydo.getNazvanie()}</a></td>
                            <td>&emsp;- weight - ${blydo.getVes()}</td>
                            <td>- prise - ${blydo.getStoimost()};</td>
                        </tr>
                    </c:if>
                </c:forEach>
            </table>

            <table>
                <br/>
                <tr><h4>&emsp;Vtorie Blyuda</h4></tr>
                <c:forEach var="blydo" items="${lb}" varStatus="status">
                    <c:if test="${razdely.get(2).equals(blydo.getKategoriya())}">
                        <tr>
                            <td><a href="mainPage?blydoname=${blydo.getNazvanie()}"
                                   name="blydoname">&emsp;${blydo.getNazvanie()}</a></td>
                            <td>&emsp;- weight - ${blydo.getVes()}</td>
                            <td>- prise - ${blydo.getStoimost()};</td>
                        </tr>
                    </c:if>
                </c:forEach>
            </table>

            <table>
                <br/>
                <tr><h4>&emsp;Desert</h4></tr>
                <c:forEach var="blydo" items="${lb}" varStatus="status">
                    <c:if test="${razdely.get(3).equals(blydo.getKategoriya())}">
                        <tr>
                            <td><a href="mainPage?blydoname=${blydo.getNazvanie()}"
                                   name="blydoname">&emsp;${blydo.getNazvanie()}</a></td>
                            <td>&emsp;- weight - ${blydo.getVes()}</td>
                            <td>- prise - ${blydo.getStoimost()};</td>
                        </tr>
                    </c:if>
                </c:forEach>
            </table>

            <br/><br/>

            <hr width="80%" align="left">

            <form METHOD="POST">

                <font color="red"><h5>${error}</h5></font>
                <table width="1200" border="0" cellpadding="5">
                    <tr>
                        <td><input TYPE="submit" name="search" value="Search"
                                   style="background-color:#40c373; height:20px"></td>
                        <td><input type="text" name="search_text" placeholder="Enter name of dish" size="106"
                                   align="left"></td>
                    </tr>
                </table>
            </form>
            <hr width="80%" align="left">
        </td>
    </tr>
</table>
</body>
</html>