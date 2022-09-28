package part_6.task_3.server;

import part_6.task_3.server.account.Account;
import part_6.task_3.server.student.Student;

import javax.xml.stream.*;
import javax.xml.stream.events.EndElement;
import javax.xml.stream.events.StartElement;
import javax.xml.stream.events.XMLEvent;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import static part_6.task_3.server.Server.fileStudent;

class ParseXmlFile {

    //запись нового студента в xml file
    public static void writeInXmlFileNewStudent(String surname, String name, String dateBorn, String faculty) throws IOException, XMLStreamException {
        System.out.println("test");
        List<Student> studentList = new ArrayList<>(parseXmlStudent(fileStudent));
        int idNewStudent = studentList.size() + 1;
        Student newStudent = new Student(idNewStudent, surname, name, dateBorn, faculty);


        studentList.add(newStudent);
        XMLOutputFactory xmlOutputFactory = XMLOutputFactory.newFactory();
        XMLStreamWriter xmlStreamWriter = xmlOutputFactory.createXMLStreamWriter(new FileOutputStream(fileStudent));
        xmlStreamWriter.writeStartDocument("1.0");
        xmlStreamWriter.writeStartElement("students");
        for (Student student : studentList) {
            xmlStreamWriter.writeStartElement("student");
            xmlStreamWriter.writeStartElement("id");
            xmlStreamWriter.writeCharacters(String.valueOf(student.getId()));
            xmlStreamWriter.writeEndElement();
            xmlStreamWriter.writeStartElement("surname");
            xmlStreamWriter.writeCharacters(student.getSurname());
            xmlStreamWriter.writeEndElement();
            xmlStreamWriter.writeStartElement("name");
            xmlStreamWriter.writeCharacters(student.getName());
            xmlStreamWriter.writeEndElement();
            xmlStreamWriter.writeStartElement("dateBorn");
            xmlStreamWriter.writeCharacters(student.getDateBorn());
            xmlStreamWriter.writeEndElement();
            xmlStreamWriter.writeStartElement("faculty");
            xmlStreamWriter.writeCharacters(student.getFaculty());
            xmlStreamWriter.writeEndElement();
            xmlStreamWriter.writeEndElement();
        }
        xmlStreamWriter.writeEndDocument();
        xmlStreamWriter.flush();
        xmlStreamWriter.close();
    }

    //редактирование дела в xml file
    public static void redactionInXmlFileStudent(int id, String namePoint, String newValuePoint) throws IOException, XMLStreamException {
        List<Student> studentList = new ArrayList<>(parseXmlStudent(fileStudent));
        for (Student student : studentList) {
            if (student.getId() == id) {
                switch (namePoint) {
                    case "surname":
                        student.setSurname(newValuePoint);
                        break;
                    case "name":
                        student.setName(newValuePoint);
                        break;
                    case "dateBorn":
                        student.setDateBorn(newValuePoint);
                        break;
                    case "faculty":
                        student.setFaculty(newValuePoint);
                        break;
                    default:
                        System.out.println("this point not found");
                        break;
                }
            }
        }
        XMLOutputFactory xmlOutputFactory = XMLOutputFactory.newFactory();
        XMLStreamWriter xmlStreamWriter = xmlOutputFactory.createXMLStreamWriter(new FileOutputStream(fileStudent));
        xmlStreamWriter.writeStartDocument("1.0");
        xmlStreamWriter.writeStartElement("students");
        for (Student studentInFile : studentList) {
            xmlStreamWriter.writeStartElement("student");
            xmlStreamWriter.writeStartElement("id");
            xmlStreamWriter.writeCharacters(String.valueOf(studentInFile.getId()));
            xmlStreamWriter.writeEndElement();
            xmlStreamWriter.writeStartElement("surname");
            xmlStreamWriter.writeCharacters(studentInFile.getSurname());
            xmlStreamWriter.writeEndElement();
            xmlStreamWriter.writeStartElement("name");
            xmlStreamWriter.writeCharacters(studentInFile.getName());
            xmlStreamWriter.writeEndElement();
            xmlStreamWriter.writeStartElement("dateBorn");
            xmlStreamWriter.writeCharacters(studentInFile.getDateBorn());
            xmlStreamWriter.writeEndElement();
            xmlStreamWriter.writeStartElement("faculty");
            xmlStreamWriter.writeCharacters(studentInFile.getFaculty());
            xmlStreamWriter.writeEndElement();
            xmlStreamWriter.writeEndElement();
        }
        xmlStreamWriter.flush();
        xmlStreamWriter.close();
    }

    //чтение xml
    public static List<Student> parseXmlStudent(String nameFile) {
        List<Student> studentsList = new ArrayList<>();
        Student student = null;
        XMLInputFactory xmlInputFactory = XMLInputFactory.newInstance();
        XMLEventReader reader = null;
        try {
            reader = xmlInputFactory.createXMLEventReader(new FileInputStream(nameFile));
            while (reader.hasNext()) {
                XMLEvent xmlEvent = reader.nextEvent();
                if (xmlEvent.isStartElement()) {
                    StartElement startElement = xmlEvent.asStartElement();
                    if (startElement.getName().getLocalPart().equals("student")) {
                        student = new Student();
                    } else if (startElement.getName().getLocalPart().equals("id")) {
                        xmlEvent = reader.nextEvent();
                        student.setId(Integer.parseInt(xmlEvent.asCharacters().getData()));
                    } else if (startElement.getName().getLocalPart().equals("surname")) {
                        xmlEvent = reader.nextEvent();
                        student.setSurname(xmlEvent.asCharacters().getData());
                    } else if (startElement.getName().getLocalPart().equals("name")) {
                        xmlEvent = reader.nextEvent();
                        student.setName(xmlEvent.asCharacters().getData());
                    } else if (startElement.getName().getLocalPart().equals("dateBorn")) {
                        xmlEvent = reader.nextEvent();
                        student.setDateBorn(xmlEvent.asCharacters().getData());
                    } else if (startElement.getName().getLocalPart().equals("faculty")) {
                        xmlEvent = reader.nextEvent();
                        student.setFaculty(xmlEvent.asCharacters().getData());
                    }
                }
                if (xmlEvent.isEndElement()) {
                    EndElement endElement = xmlEvent.asEndElement();
                    if (endElement.getName().getLocalPart().equals("student")) {
                        studentsList.add(student);
                    }
                }
            }

        } catch (Exception exc) {
            exc.printStackTrace();
        } finally {
            try {
                reader.close();
            } catch (XMLStreamException e) {
                throw new RuntimeException(e);
            }
        }
        return studentsList;
    }

    public static List<Account> parseXmlAccount(String nameFile) {
        List<Account> accountList = new ArrayList<>();
        Account account = null;
        XMLInputFactory xmlInputFactory = XMLInputFactory.newInstance();
        try {
            XMLEventReader reader = xmlInputFactory.createXMLEventReader(new FileInputStream(nameFile));
            while (reader.hasNext()) {
                XMLEvent xmlEvent = reader.nextEvent();
                if (xmlEvent.isStartElement()) {
                    StartElement startElement = xmlEvent.asStartElement();
                    if (startElement.getName().getLocalPart().equals("account")) {
                        account = new Account();
                    } else if (startElement.getName().getLocalPart().equals("login")) {
                        xmlEvent = reader.nextEvent();
                        account.setLogin(xmlEvent.asCharacters().getData());
                    } else if (startElement.getName().getLocalPart().equals("password")) {
                        xmlEvent = reader.nextEvent();
                        account.setPassword(xmlEvent.asCharacters().getData());
                    } else if (startElement.getName().getLocalPart().equals("admin")) {
                        xmlEvent = reader.nextEvent();
                        account.setAdministrator((Boolean.parseBoolean(xmlEvent.asCharacters().getData())));
                    }
                }
                if (xmlEvent.isEndElement()) {
                    EndElement endElement = xmlEvent.asEndElement();
                    if (endElement.getName().getLocalPart().equals("account")) {
                        accountList.add(account);
                    }
                }
            }
        } catch (Exception exc) {
            exc.printStackTrace();
        }
        return accountList;
    }

}




