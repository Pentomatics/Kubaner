package Kubaner.GUI;

import Kubaner.Logic.*;

import java.awt.BorderLayout;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.*;

public class InputMaskStudent extends JFrame implements ActionListener{
	
	private PlanGenerator planGenerator;
	private Plan plan;
	private SubjectList subList;
	private StudentList currentStudentList;
	private Student currentStudent;
	private Time start, end;
	private TimePeriod[] periode = new TimePeriod[1];
	private int subjectListSize = subList.size();
	private JButton confirmButton, cancelButton;
	private JRadioButton[] subjectListButtons = new JRadioButton[subjectListSize];
	private JPanel itemsPanel, namePanel, subjectPanel, selectionSubjectPanel, timePanel, timeStartPanel, timeEndPanel, controlPanel;
	private JTextField nameField;
	private JLabel nameLabel, subjectLabel, timeStartLabel, timeEndLabel ;
	private JSpinner startTimeHours, startTimeMinutes, endTimeHours, endTimeMinutes;
	private SpinnerNumberModel startTimeHoursModel, startTimeMinutesModel, endTimeHoursModel, endTimeMinutesModel;
	private String name;
	private int startHour, startMinute, endHour, endMinute;
	private Subject[] teachingSubject;

//	public static void main (String[] args) {
//		new ImportMaskProfessor(plan, planGenerator).setVisible(true);
//	}
	
	InputMaskStudent(Plan plan, PlanGenerator planGenertor){
		
		this.plan = plan;
		this.planGenerator = planGenerator;
		
		subList = planGenerator.getSubjectList();
		currentStudentList = planGenerator.getStudentList();
		
		setTitle("Student-Eingabemaske");
		setLayout(new BorderLayout());
		
		itemsPanel = new JPanel();
		itemsPanel.setLayout(new GridLayout(3,1));
		
		//Erstellt ein Panel f�r die Namenseingabe
		namePanel = new JPanel();
		namePanel.setLayout(new GridLayout(2,1));
		namePanel.setBorder(BorderFactory.createEmptyBorder(10, 10, 10, 10));
		nameLabel = new JLabel("Name des Student (Pflichtfeld): ");
		nameField = new JTextField("Max Mustermann");
		namePanel.add(nameLabel);
		namePanel.add(nameField);
		
		//Erstellt ein Panel f�r die Fachauswahl.
		subjectPanel = new JPanel();
		subjectPanel.setLayout(new GridLayout(2,1));
		subjectPanel.setBorder(BorderFactory.createEmptyBorder(10, 10, 10, 10));
		subjectLabel = new JLabel("Fach des Student (Pflichtfeld): ");
		
		//Erstellt ein panel f�r die RadioButtons der F�cher.
		selectionSubjectPanel = new JPanel();
		selectionSubjectPanel.setLayout(new GridLayout((subjectListSize)/2, 2));
		for (int i = 0; i != subjectListSize; i++){
			subjectListButtons[i] = new JRadioButton((subList.get(i)).getName());
			selectionSubjectPanel.add(subjectListButtons[i]);
			subjectListButtons[i].addActionListener(this);
		}
		subjectPanel.add(subjectLabel);
		subjectPanel.add(selectionSubjectPanel);
		
		//Erstellt ein Panel f�r die Wunschzeit
		timePanel = new JPanel();
		timePanel.setLayout(new GridLayout(4, 1));
		timePanel.setBorder(BorderFactory.createEmptyBorder(10, 10, 10, 10));
		timeStartLabel = new JLabel ("Wunschstartzeit des Student (optional): ");
		timePanel.add(timeStartLabel);
		
		//Erstellt ein Panel f�r die Startzeit.
		timeStartPanel = new JPanel();
		timeStartPanel.setLayout(new GridLayout(1,2));
		startTimeHoursModel = new SpinnerNumberModel(0,0,23, 1);
		startTimeMinutesModel = new SpinnerNumberModel(0,0,59, 1);
		startTimeHours = new JSpinner(startTimeHoursModel);
		startTimeMinutes = new JSpinner(startTimeMinutesModel);
	    timeStartPanel.add(startTimeHours);
	    timeStartPanel.add(startTimeMinutes);
		timePanel.add(timeStartPanel);
		
		//Erstellt ein Panel f�r die Endzeit.
		timeEndLabel = new JLabel("Wunschendzeit des Student (optional): ");
		timePanel.add(timeEndLabel);
		timeEndPanel = new JPanel();
		timeEndPanel.setLayout(new GridLayout(1,2));
		endTimeHoursModel = new SpinnerNumberModel(0,0,23, 1);
		endTimeMinutesModel = new SpinnerNumberModel(0,0,59, 1);
		endTimeHours = new JSpinner(endTimeHoursModel);
		endTimeMinutes = new JSpinner(endTimeMinutesModel);
	    timeEndPanel.add(endTimeHours);
	    timeEndPanel.add(endTimeMinutes);
		timePanel.add(timeEndPanel);
		
		//Erstellt ein Pnale zum best�tigen und zum abbrechen der Eingabe
		controlPanel = new JPanel();
		confirmButton = new JButton("Best�tigen");
		cancelButton = new JButton("Abbrechen");
		controlPanel.add(confirmButton);
		controlPanel.add(cancelButton);
		
		//F�gt ActionListener f�r die beiden kn�pfe hinzu.
		confirmButton.addActionListener(this);
		cancelButton.addActionListener(this);
		
		itemsPanel.add(namePanel);
		itemsPanel.add(subjectPanel);
		itemsPanel.add(timePanel);
		add(itemsPanel, BorderLayout.CENTER);
		add(controlPanel, BorderLayout.SOUTH);
		pack();
		setLocationRelativeTo(null);
	}


	@Override
	public void actionPerformed(ActionEvent e) {
		if (e.getSource() == cancelButton){
			System.exit(0);
		} else if (e.getSource() == confirmButton){
			name = nameField.getText();
			startHour = (int) startTimeHours.getValue();
			startMinute = (int) startTimeMinutes.getValue();
			endHour = (int) endTimeHours.getValue();
			endMinute = (int) endTimeMinutes.getValue();
			
			//Z�hlt die ausgew�hleten F�cher.
			int counterSubjects = 0;
			for (int index = 0; index != subjectListSize ; index++){
				if(subjectListButtons[index].isSelected())
					counterSubjects++;
			}
			
			//F�gt die unterichtenden F�cher in eine F�cherListe ein.
			//teachingSubject= new Subject[counterSubjects];
			int counter = 0;
			
			for (int index = 0; index != subjectListSize ; index++){
				if(subjectListButtons[index].isSelected())
					//teachingSubject[counter] = SubjectList.get(index); 
					counter++;
			}
			
			start = new Time(startHour, startMinute);
			end = new Time(endHour, endMinute);
			periode[0] = new TimePeriod(start, end);
			
//			Testausgabe
//			System.out.println("Name: " + professorName);
//			for (int test = 0; test != counterSubjects; test++)
//				System.out.println(test +"Fach: " + teachingSubject[],getName());
//				System.out.println(test +"Fach: ");
//			System.out.println("StartZeit: "+ startHour + ":" + startMinute);
//			System.out.println("EndZeit: "+ endHour + ":" + endMinute);
			
			try{
					currentStudent = currentStudentList.create(name, teachingSubject, periode);
					System.exit(0);
			} catch (IllegalArgumentException E){
				JOptionPane.showMessageDialog(null, "Ihre Eingabe war fehlerhaft oder unvollst�ndig!", "Fehler beim Erstellen", JOptionPane.CANCEL_OPTION);
			}
		}
		
	}
}

