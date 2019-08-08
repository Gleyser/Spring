package br.com.pessoa.util;

import java.io.StringWriter;

import javax.management.RuntimeErrorException;
import javax.xml.bind.JAXBContext;
import javax.xml.bind.JAXBException;
import javax.xml.bind.Marshaller;
import javax.xml.stream.XMLStreamWriter;

import org.codehaus.jettison.mapped.MappedNamespaceConvention;
import org.codehaus.jettison.mapped.MappedXMLStreamWriter;

import br.com.pessoa.domain.Carro;
import br.com.pessoa.domain.ListaCarros;

public class JAXBUtil {
	private static JAXBUtil instance;
	private static JAXBContext context;
	public static JAXBUtil getInstance() {
		return instance;
	}
	
	static {
		try {
			context = JAXBContext.newInstance(ListaCarros.class, Carro.class);
		} catch(JAXBException e) {
			throw new RuntimeException(e);
		}
	}
	
	public static String toXML(Object object) {		
		try {
			StringWriter writer = new StringWriter();
			Marshaller m = context.createMarshaller();
			m.setProperty(Marshaller.JAXB_FORMATTED_OUTPUT, true);
			m.marshal(object, writer);
			String xml = writer.toString();
			return xml;
			
		} catch (JAXBException e) {
			e.printStackTrace();
			return null;
		}
	}
		
		public static String toJSON(Object object) {		
			try {
				StringWriter writer = new StringWriter();
				Marshaller m = context.createMarshaller();
				MappedNamespaceConvention con = new MappedNamespaceConvention();
				XMLStreamWriter xmlStreamWriter = new MappedXMLStreamWriter(con, writer);
				m.marshal(object, xmlStreamWriter);	
				String json = writer.toString();
				return json;
				
			} catch (JAXBException e) {
				e.printStackTrace();
				return null;
			}
			
		}
		
	}


