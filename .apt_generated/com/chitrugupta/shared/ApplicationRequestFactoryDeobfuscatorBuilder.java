// Automatically Generated -- DO NOT EDIT
// com.chitrugupta.shared.ApplicationRequestFactory
package com.chitrugupta.shared;
import java.util.Arrays;
import com.google.web.bindery.requestfactory.vm.impl.OperationData;
import com.google.web.bindery.requestfactory.vm.impl.OperationKey;
public final class ApplicationRequestFactoryDeobfuscatorBuilder extends com.google.web.bindery.requestfactory.vm.impl.Deobfuscator.Builder {
{
withOperation(new OperationKey("AWVxlfuYUPBzF7kbWfoozL0LYHI="),
  new OperationData.Builder()
  .withClientMethodDescriptor("(Lcom/chitrugupta/shared/EmployeeProxy;)Lcom/google/web/bindery/requestfactory/shared/Request;")
  .withDomainMethodDescriptor("(Lcom/chitrugupta/server/Employee;)Z")
  .withMethodName("editemp")
  .withRequestContext("com.chitrugupta.shared.EmployeeRequestContext")
  .build());
withOperation(new OperationKey("W1hOYZ4zVokXnti0Plxfm5iSalU="),
  new OperationData.Builder()
  .withClientMethodDescriptor("(I)Lcom/google/web/bindery/requestfactory/shared/Request;")
  .withDomainMethodDescriptor("(I)Z")
  .withMethodName("RemoveEmployee")
  .withRequestContext("com.chitrugupta.shared.EmployeeRequestContext")
  .build());
withOperation(new OperationKey("TjdWaeyd$V1lXLv6mPh03NW_KiY="),
  new OperationData.Builder()
  .withClientMethodDescriptor("()Lcom/google/web/bindery/requestfactory/shared/Request;")
  .withDomainMethodDescriptor("()Ljava/util/List;")
  .withMethodName("allEmployees")
  .withRequestContext("com.chitrugupta.shared.EmployeeRequestContext")
  .build());
withOperation(new OperationKey("eD2fcxOZHwov4oIBuL9Jccrtpnw="),
  new OperationData.Builder()
  .withClientMethodDescriptor("(I)Lcom/google/web/bindery/requestfactory/shared/Request;")
  .withDomainMethodDescriptor("(I)Lcom/chitrugupta/server/Employee;")
  .withMethodName("checkemp")
  .withRequestContext("com.chitrugupta.shared.EmployeeRequestContext")
  .build());
withOperation(new OperationKey("8Kkd7pnYDBQCI0YJ214f0aHQbPg="),
  new OperationData.Builder()
  .withClientMethodDescriptor("(I)Lcom/google/web/bindery/requestfactory/shared/Request;")
  .withDomainMethodDescriptor("(I)Lcom/chitrugupta/server/Employee;")
  .withMethodName("queryEmployee")
  .withRequestContext("com.chitrugupta.shared.EmployeeRequestContext")
  .build());
withOperation(new OperationKey("Jp2LE6tQ53jFiF5ABJaZ0NYx17A="),
  new OperationData.Builder()
  .withClientMethodDescriptor("(Lcom/chitrugupta/shared/EmployeeProxy;)Lcom/google/web/bindery/requestfactory/shared/Request;")
  .withDomainMethodDescriptor("(Lcom/chitrugupta/server/Employee;)Z")
  .withMethodName("addEmployee")
  .withRequestContext("com.chitrugupta.shared.EmployeeRequestContext")
  .build());
withRawTypeToken("Y6tTxUbWDv1x2jpiiGFAjbpRzFo=", "com.chitrugupta.shared.EmployeeProxy");
withRawTypeToken("8KVVbwaaAtl6KgQNlOTsLCp9TIU=", "com.google.web.bindery.requestfactory.shared.ValueProxy");
withRawTypeToken("FXHD5YU0TiUl3uBaepdkYaowx9k=", "com.google.web.bindery.requestfactory.shared.BaseProxy");
withClientToDomainMappings("com.chitrugupta.server.Employee", Arrays.asList("com.chitrugupta.shared.EmployeeProxy"));
}}
