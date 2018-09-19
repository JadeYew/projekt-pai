<?xml version="1.0" encoding="UTF-8"?>
<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
pageEncoding="ISO-8859-1"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<!DOCTYPE html>
<html xmlns="http://www.w3.org/1999/xhtml"
      xmlns:h="http://xmlns.jcp.org/jsf/html"
      xmlns:ui="http://xmlns.jcp.org/jsf/facelets"
      xmlns:f="http://java.sun.com/jsf/core"
      xmlns:p="http://primefaces.org/ui">
    <h:head>
        <title>Wyporzyczalnia - rejestracja</title>
        <meta name="viewport" content="width=device-width, initial-scale=1.0"/>
    </h:head>
    <body>
        <ui:composition template="default.xhtml">
            <ui:define name="contents">
                <%
                 String name="Iwo";
                 String email="Ryszkowski";
                  %>
                  aaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaa
                  <h:form>
                      <input hidden="true" name="password" value=<%=name %>/>
                      <input hidden="true" name="passwordAgain" value=<%=email %>/>
                      <commandButton value="dalej" ajax="false" action="${sesja.test(password, passwordAgain)}"/>             
                  </h:form>
            </ui:define>
        </ui:composition>
</body>
</html>