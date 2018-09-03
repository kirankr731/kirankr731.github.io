package com.chitrugupta.client;
import java.util.Iterator;
import java.util.List;

import org.eclipse.jetty.security.authentication.LoginAuthenticator;

import com.chitrugupta.shared.ApplicationRequestFactory;
import com.chitrugupta.shared.EmployeeProxy;
import com.chitrugupta.shared.EmployeeRequestContext;
import com.chitrugupta.shared.FieldVerifier;
import com.google.gwt.core.client.EntryPoint;
import com.google.gwt.core.client.GWT;
import com.google.gwt.event.dom.client.ClickEvent;
import com.google.gwt.event.dom.client.ClickHandler;
import com.google.gwt.event.shared.EventBus;
import com.google.gwt.event.shared.SimpleEventBus;
import com.google.gwt.user.client.ui.Button;
import com.google.gwt.user.client.ui.DialogBox;
import com.google.gwt.user.client.ui.FlexTable;
import com.google.gwt.user.client.ui.Label;
import com.google.gwt.user.client.ui.RootPanel;
import com.google.gwt.user.client.ui.TextBox;
import com.google.gwt.user.client.ui.VerticalPanel;
import com.google.web.bindery.requestfactory.shared.Receiver;

/**
 * Entry point classes define <code>onModuleLoad()</code>.
 */
public class Emp implements EntryPoint {
	/**
	 * The message displayed to the user when the server cannot be reached or
	 * returns an error.
	 */
	private static final String SERVER_ERROR = "An error occurred while "
			+ "attempting to contact the server. Please check your network " + "connection and try again.";

	/**
	 * Create a remote service proxy to talk to the server-side Greeting service.
	 */
	//private final GreetingServiceAsync greetingService = GWT.create(GreetingService.class);
	
	/**
	 * This is the entry point method.
	 */
	private final ApplicationRequestFactory factory = GWT.create(ApplicationRequestFactory.class);
	public void onModuleLoad() {
		final EventBus eventBus = new SimpleEventBus();
		factory.initialize(eventBus);
		final VerticalPanel vp = new VerticalPanel();
		final Button b1 = new Button("Add Employee");
		b1.setStyleName("sizemod");
		b1.setWidth("250px");
		final Button query=new Button("Query Employee");
		query.setStyleName("sizemod");
		query.setWidth("250px");
		final Button removebutton=new Button("Remove an Employee");
		removebutton.setStyleName("sizemod");
		removebutton.setWidth("250px");
		final Button editemp=new Button("Edit Employee");
		editemp.setStyleName("sizemod");
		editemp.setWidth("250px");
		editemp.addClickHandler(new ClickHandler() {
			
			@Override
			public void onClick(ClickEvent event) {
				final DialogBox editempdb=new DialogBox();
				editempdb.setStyleName("dropboxstyle");
				editempdb.setAnimationEnabled(true);
				final FlexTable editempflext=new FlexTable();
				editempflext.setWidget(1, 1, new Label("Please provide the id to edit"));
				final TextBox idtb=new TextBox();
				final Button closeedit=new Button("Close");
				editempflext.setWidget(1,2,idtb);
				final Button subeButton=new Button("Submit");
				editempflext.setWidget(2,3,subeButton);
				editempflext.setWidget(3, 3, closeedit);
				editempdb.center();
				closeedit.addClickHandler(new ClickHandler() {
					
					@Override
					public void onClick(ClickEvent event) {
						editempdb.hide();
					}
				});
				editempdb.add(editempflext);
				subeButton.addClickHandler(new ClickHandler() {
					
					@Override
					public void onClick(ClickEvent event) {
						final EmployeeRequestContext reqedit=factory.employeeRequestContext();
						final int id=Integer.parseInt(idtb.getText());
						reqedit.checkemp(id).fire(new Receiver<EmployeeProxy>() {

							@Override
							public void onSuccess(EmployeeProxy response) {
								if(response==null)
								{
									final DialogBox faileditdDialogBox =new DialogBox();
									faileditdDialogBox.setStyleName("dropboxstyle");
									faileditdDialogBox.setAnimationEnabled(true);
									final FlexTable editfailFlexTable =new FlexTable();
									editfailFlexTable.setWidget(0, 1, new Label("Enter proper id"));
									final Button closefailedit=new Button("Close");
									editfailFlexTable.setWidget(1, 3, closefailedit);
									closefailedit.addClickHandler(new ClickHandler() {
										@Override
										public void onClick(ClickEvent event) {
											faileditdDialogBox.hide();
											
										}
									});
									faileditdDialogBox.add(editfailFlexTable);
									faileditdDialogBox.center();
								}
								else
								{
									final DialogBox successeditBox=new DialogBox();
									successeditBox.setStyleName("dropboxstyle");
									final FlexTable successeditFlexTable =new FlexTable();
									successeditFlexTable.setWidget(0,2,new Label("Edit Employee details"));
									final Label id=new Label();
									id.setText(String.valueOf(response.getId()));
									successeditFlexTable.setWidget(1, 1, new Label("ID"));
									successeditFlexTable.setWidget(1, 2, id);
									final TextBox name=new TextBox();
									name.setText(response.getName());
									successeditFlexTable.setWidget(2,1,new Label("Name"));
									successeditFlexTable.setWidget(2,2,name);
									final TextBox email=new TextBox();
									email.setText(response.getEmail());
									successeditFlexTable.setWidget(3,1,new Label("Email"));
									successeditFlexTable.setWidget(3,2,email);
									final TextBox salary=new TextBox();
									salary.setText(String.valueOf(response.getSalary()));
									successeditFlexTable.setWidget(4,1,new Label("Salary"));
									successeditFlexTable.setWidget(4,2,salary);
									final Button editactual=new Button("Edit");
									successeditFlexTable.setWidget(5, 3, editactual);
									editactual.addClickHandler(new ClickHandler() {
										
										@Override
										public void onClick(ClickEvent event) {
											final EmployeeRequestContext reqedit=factory.employeeRequestContext();
											EmployeeProxy empp=reqedit.create(EmployeeProxy.class);
											empp.setEmail(email.getText());
											empp.setId(Integer.parseInt(id.getText()));
											empp.setName(name.getText());
											empp.setSalary(Integer.parseInt(salary.getText()));
											reqedit.editemp(empp).fire(new Receiver<Boolean>() {

												@Override
												public void onSuccess(Boolean response) {
													if(response==true)
													{
														final DialogBox successedit=new DialogBox();
														successedit.setStyleName("dropboxstyle");
														final FlexTable successeditft=new FlexTable();
														successeditft.setWidget(0, 1, new Label("Successfully Edited"));
														final Button succlose =new Button("close");
														succlose.addClickHandler(new ClickHandler() {
															
															@Override
															public void onClick(ClickEvent event) {
																successedit.hide();
																
															}
														});
														successeditft.setWidget(1, 3, succlose);
														successedit.add(successeditft);
														successedit.center();
													}
													
													
												}
											});
											
										}
									});
									final Button editcl=new Button("close");
									editcl.addClickHandler(new ClickHandler() {
										
										@Override
										public void onClick(ClickEvent event) {
											successeditBox.hide();
											
										}
									});
									successeditFlexTable.setWidget(6,3,editcl);
									successeditBox.add(successeditFlexTable);
									successeditBox.center();
									
								}
								
								
							}
						});
					}
				});
					}
				});
		final Button allEmpl=new Button("Display All Employees");
		allEmpl.setStyleName("sizemod");
		allEmpl.setWidth("250px");
		allEmpl.addClickHandler(new ClickHandler() {
			@Override
			public void onClick(ClickEvent event) {
				final EmployeeRequestContext requestall=factory.employeeRequestContext();
				final DialogBox allempDialog=new DialogBox();
				allempDialog.setStyleName("dropboxstyle");
				allempDialog.setAnimationEnabled(true);
				final FlexTable allEmplFlexTable=new FlexTable();
				requestall.allEmployees().fire(new Receiver<List<EmployeeProxy>>(){
					
					@Override
					public void onSuccess(List<EmployeeProxy> result) {
						if(result!=null)
						{
							allEmplFlexTable.setWidget(0, 4, new Label("ID"));
							allEmplFlexTable.setWidget(0, 8, new Label("Name"));
							allEmplFlexTable.setWidget(0, 12, new Label("Email"));
							allEmplFlexTable.setWidget(0, 16, new Label("Salary"));
							Iterator<EmployeeProxy> it=result.iterator();
							int i=1;
							while(it.hasNext())
							{
								EmployeeProxy emp=it.next();
								allEmplFlexTable.setWidget(i,4,new Label(String.valueOf(emp.getId())));
								allEmplFlexTable.setWidget(i,8,new Label(emp.getName()));
								allEmplFlexTable.setWidget(i,12,new Label(emp.getEmail()));
								allEmplFlexTable.setWidget(i,16,new Label(String.valueOf(emp.getSalary())));
								i++;
							}
							Button closedisp=new Button("close");
							allEmplFlexTable.setWidget(i,16,closedisp);
							allempDialog.add(allEmplFlexTable);
							allempDialog.center();
							closedisp.addClickHandler(new ClickHandler() {
								
								@Override
								public void onClick(ClickEvent event) {
									allempDialog.hide();
									
								}
							});
						}
						else
						{
							final DialogBox allsuc=new DialogBox();
							allsuc.setStyleName("dropboxstyle");
							final FlexTable aflexsuc=new FlexTable();
							aflexsuc.setWidget(0, 1, new Label("No Records of Employee found"));
							final Button closeallemp=new Button("close");
							aflexsuc.setWidget(1,2,closeallemp);
							allsuc.add(aflexsuc);
							allsuc.center();
							closeallemp.addClickHandler(new ClickHandler() {
								
								@Override
								public void onClick(ClickEvent event) {
									
									allsuc.hide();
								}
							});
						}
						
					}
				});
				
			}
		});
		removebutton.addClickHandler(new ClickHandler() {
			@Override
			public void onClick(ClickEvent event) {
				final EmployeeRequestContext requestall=factory.employeeRequestContext();
				final DialogBox removedlog=new DialogBox();
				removedlog.setStyleName("dropboxstyle");
				removedlog.setAnimationEnabled(true);
				FlexTable rflex=new FlexTable();
				Label rlabel=new Label("Enter Employee ID");
				final TextBox idtext=new TextBox();
				final Button removee=new Button("Remove");
				rflex.setWidget(0, 1, rlabel);
				rflex.setWidget(0, 2, idtext);
				rflex.setWidget(1, 3, removee);
				final Button closerem=new Button("close");
				rflex.setWidget(2, 3, closerem);
				closerem.addClickHandler(new ClickHandler() {
					
					@Override
					public void onClick(ClickEvent event) {
						
						removedlog.hide();
					}
				});
				removedlog.add(rflex);
				removedlog.center();
				removee.addClickHandler(new ClickHandler() {
					
					@Override
					public void onClick(ClickEvent event) {
						removedlog.hide();
						int id=Integer.parseInt(idtext.getText());
						requestall.RemoveEmployee(id).fire(new Receiver<Boolean>(){
							
							@Override
							public void onSuccess(Boolean result) {
								if(result ==true)
								{
									final DialogBox succ=new DialogBox();
									succ.setStyleName("dropboxstyle");
									final FlexTable sucflex=new FlexTable();
									sucflex.setWidget(0, 1, new Label("Employee Removed Successfully"));
									final Button closerem=new Button("close");
									sucflex.setWidget(1,2,closerem);
									succ.add(sucflex);
									succ.center();
									closerem.addClickHandler(new ClickHandler() {
										
										@Override
										public void onClick(ClickEvent event) {
											
											succ.hide();
										}
									});
								}
								else
								{
									final DialogBox remsuc=new DialogBox();
									remsuc.setStyleName("dropboxstyle");
									final FlexTable rflexsuc=new FlexTable();
									rflexsuc.setWidget(0, 1, new Label("Employee not found bro"));
									final Button closeremfail=new Button("close");
									rflexsuc.setWidget(1,2,closeremfail);
									remsuc.add(rflexsuc);
									remsuc.center();
									closeremfail.addClickHandler(new ClickHandler() {
										
										@Override
										public void onClick(ClickEvent event) {
											
											remsuc.hide();
										}
									});
								}
								
							}
						});
					}
				});
			}
		});
		
		query.addClickHandler(new ClickHandler() {
			@Override
			public void onClick(ClickEvent event) {
				final EmployeeRequestContext requestall=factory.employeeRequestContext();
				final DialogBox query=new DialogBox();
				query.setStyleName("dropboxstyle");
				query.setAnimationEnabled(true);
				FlexTable qflex=new FlexTable();
				Label qlabel=new Label("Enter Employee ID");
				final TextBox idtext=new TextBox();
				final Button queryy=new Button("Query");
				qflex.setWidget(0, 1,qlabel);
				qflex.setWidget(0,2,idtext);
				qflex.setWidget(2, 3, queryy);
				final Button closequery=new Button("close");
				qflex.setWidget(3, 3, closequery);
				closequery.addClickHandler(new ClickHandler() {
					
					@Override
					public void onClick(ClickEvent event) {
						
						query.hide();
					}
				});
				queryy.addClickHandler(new ClickHandler() {
					
					@Override
					public void onClick(ClickEvent event) {
						query.hide();
						int id=Integer.parseInt(idtext.getText());
						requestall.queryEmployee(id).fire(new Receiver<EmployeeProxy>(){


							@Override
							public void onSuccess(EmployeeProxy result) {
								if(result!=null)
								{
								final DialogBox querysuc=new DialogBox();
								querysuc.setStyleName("dropboxstyle");
								final FlexTable qflexsuc=new FlexTable();
								final Button closequery=new Button("close");
								qflexsuc.setWidget(0, 1, new Label("Employee id"));
								qflexsuc.setWidget(0, 2, new Label(String.valueOf(result.getId())));
								qflexsuc.setWidget(1, 1, new Label("Employee Name"));
								qflexsuc.setWidget(1, 2, new Label(result.getName()));
								qflexsuc.setWidget(2, 1, new Label("Employee Email"));
								qflexsuc.setWidget(2, 2, new Label(result.getEmail()));
								qflexsuc.setWidget(3,1,new Label("Salary"));
								qflexsuc.setWidget(3,2,new Label(String.valueOf(result.getSalary())));
								qflexsuc.setWidget(4,3,closequery);
								querysuc.add(qflexsuc);
								querysuc.center();
								closequery.addClickHandler(new ClickHandler() {
									
									@Override
									public void onClick(ClickEvent event) {
										
										querysuc.hide();
									}
								});
								
							}
								else
								{
									final DialogBox querysuc=new DialogBox();
									querysuc.setStyleName("dropboxstyle");
									final FlexTable qflexsuc=new FlexTable();
									qflexsuc.setWidget(0, 1, new Label("Employee not found bro"));
									final Button closequery=new Button("close");
									qflexsuc.setWidget(1,2,closequery);
									querysuc.add(qflexsuc);
									querysuc.center();
									closequery.addClickHandler(new ClickHandler() {
										
										@Override
										public void onClick(ClickEvent event) {
											
											querysuc.hide();
										}
									});
								}
							}

						});
						
					}
				});
				query.add(qflex);
				query.center();
				
			}
		});
		b1.addClickHandler(new ClickHandler() {
			@Override
			public void onClick(ClickEvent event) {
				final DialogBox db = new DialogBox();
				db.setStyleName("dropboxstyle");
				db.setAnimationEnabled(true);
				db.center();
				FlexTable flexb = new FlexTable();
				flexb.setWidget(0, 2, new Label("Provide Employee Details"));
				Label eidlabel = new Label("Employee ID");
				final TextBox eidtextbox = new TextBox();
				Label namelabel = new Label("Name");
				final TextBox nametextbox = new TextBox();
				Label emaillabel = new Label("Email");
				final TextBox emailtext = new TextBox();
				Label salary = new Label("Salary");
				final TextBox salarytext = new TextBox();
				flexb.setWidget(1, 1, eidlabel);
				flexb.setWidget(1, 2, eidtextbox);
				flexb.setWidget(2, 1, namelabel);
				flexb.setWidget(2, 2, nametextbox);
				flexb.setWidget(3, 1, emaillabel);
				flexb.setWidget(3, 2, emailtext);
				flexb.setWidget(4, 1, salary);
				flexb.setWidget(4, 2, salarytext);
				Button submit = new Button("Submit");
				flexb.setWidget(5, 3, submit);
				final Button closeadd=new Button("close");
				flexb.setWidget(6,3,closeadd);
				closeadd.addClickHandler(new ClickHandler() {
					
					@Override
					public void onClick(ClickEvent event) {
						
						db.hide();
					}
				});
				
				db.add(flexb);
				submit.addClickHandler(new ClickHandler() {

					@Override
					public void onClick(ClickEvent event) {
						boolean flag=true;
						int id=0;
						String eid = eidtextbox.getText();
						try {
						id = Integer.parseInt(eid);
						}
						catch (Exception e) {
							flag=false;
							final DialogBox namedb=new DialogBox();
							namedb.setStyleName("dropboxstyle");
							final Label namelabel=new Label("Enter proper ID ");
							final FlexTable ft=new FlexTable();
							ft.setWidget(0, 1, namelabel);
							final Button closeadd2=new Button("close");
							ft.setWidget(1,2,closeadd2);
							namedb.add(ft);
							namedb.center();
							closeadd2.addClickHandler(new ClickHandler() {
								
								@Override
								public void onClick(ClickEvent event) {
									
									namedb.hide();
								}
							});
						}
						String name =nametextbox.getText();
						if(!FieldVerifier.isValidName(name))
						{
							flag=false;
							final DialogBox namedb=new DialogBox();
							namedb.setStyleName("dropboxstyle");
							final Label namelabel=new Label("Enter proper Name");
							final FlexTable ft=new FlexTable();
							ft.setWidget(0, 1, namelabel);
							final Button closeadd2=new Button("close");
							ft.setWidget(1,2,closeadd2);
							namedb.add(ft);
							namedb.center();
							closeadd2.addClickHandler(new ClickHandler() {
								
								@Override
								public void onClick(ClickEvent event) {
									
									namedb.hide();
								}
							});
						}
						String email = emailtext.getText();
						String salarystr = salarytext.getText();
						int salary =0;
						try {
							salary = Integer.parseInt(salarystr);
						}
						catch (NumberFormatException e) {
							flag=false;
							final DialogBox namedb=new DialogBox();
							namedb.setStyleName("dropboxstyle");
							final Label namelabel=new Label("Enter proper Salary");
							final FlexTable ft=new FlexTable();
							ft.setWidget(0, 1, namelabel);
							final Button closeadd2=new Button("close");
							ft.setWidget(1,2,closeadd2);
							namedb.add(ft);
							namedb.center();
							closeadd2.addClickHandler(new ClickHandler() {
								
								@Override
								public void onClick(ClickEvent event) {
									
									namedb.hide();
								}
							});
							
						}
						final EmployeeRequestContext requestadd=factory.employeeRequestContext();
						EmployeeProxy e=requestadd.create(EmployeeProxy.class);
						e.setEmail(email);
						e.setId(id);
						e.setName(name);
						e.setSalary(salary);
						if(flag==true)
						{
								requestadd.addEmployee(e).fire(new Receiver<Boolean>() {
									
									@Override
									public void onSuccess(Boolean result) {
										db.hide();
										if(result==true)
										{
										final DialogBox dialog=new DialogBox();
										dialog.setStyleName("dropboxstyle");
										Label succ=new Label("Employee added successfully");
										FlexTable success=new FlexTable();
										Button closeaddButton=new Button("close");
										success.setWidget(0, 1, succ);
										success.setWidget(1, 2, closeaddButton);
										dialog.add(success);
										dialog.center();
										closeaddButton.addClickHandler(new ClickHandler() {
											
											@Override
											public void onClick(ClickEvent event) {
												dialog.hide();
												
											}
										});
										}
										else
										{
											Label succ=new Label("Another employee found with this id found");
											FlexTable success=new FlexTable();
											Button closeaddButton=new Button("close");
											success.setWidget(0, 1, succ);
											success.setWidget(1, 2, closeaddButton);
											final DialogBox dialog2=new DialogBox();
											dialog2.setStyleName("dropboxstyle");;
											dialog2.add(success);
											dialog2.center();
											closeaddButton.addClickHandler(new ClickHandler() {
												
												@Override
												public void onClick(ClickEvent event) {
													dialog2.hide();
													
												}
											});
										}
										
									}
								});
						}
					}
				});
			}
		});
		vp.add(b1);
		vp.add(query);
		vp.add(editemp);
		vp.add(removebutton);
		vp.add(allEmpl);
		RootPanel.get().add(vp);	
	}
}
