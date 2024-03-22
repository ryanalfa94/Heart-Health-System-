# Heart-Health-System-
Heart Health Imaging and Recording System Functionalities: 

Main View:

Receptionist View:

This software system will help heart health imaging labs to keep patient records as well as patients to see their test/imaging results. Suppose this system is to store CT scan related data for heart patients. Before the CT scan, receptionist takes patient information and saves them. Receptionists request the following data from the patient.


Patient Intake Form

Once all the information is taken from the patient, the receptionist creates an entry for the patent and schedules the exam date for the patient. At this point a unique 5 digit patient ID will be created. Then the patient information will be stored in a file. Name the file using the five digit patient ID. For example, if the patient is 12345 then the file for the patient is 12345_PatientInfo.txt

Technician View (Entering Data):

During the exam time, the technician performs the CT scan and records the following data to the patient record. The CT scan data enter view looks like the following. Once the technician fills all the data items, the patient CT Scan data file will be created with the following CT Scan data. For example, if the patient file is 12345_PatientInfo.txt then 12345CTResults.txt file will be created with the following data. Make sure all the fields are filled before updating the file. If any of the data items are missing an error message should be shown to the technician and the file will not be modified

Patient View

When the patient enters patient ID to view data, the above report will be shown to the user with data filled in each of the boxes above by accessing the patient Info as well as the CT Scan files. Foe example, if the patient ID is 12345 then 12345_PatientInfo.txt then 12345CTResults.txt will be accessed to produce the above report.  If the CT scan data is not available, a message will be displayed to the user indicating no data is available yet. if no such patient is available or the patient ID is entered incorrectly, display the error message wrong patient ID
