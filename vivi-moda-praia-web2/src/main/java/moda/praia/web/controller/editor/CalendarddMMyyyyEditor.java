package moda.praia.web.controller.editor;

import java.beans.PropertyEditorSupport;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.GregorianCalendar;

import org.apache.log4j.Logger;
import org.springframework.stereotype.Component;


@Component
public class CalendarddMMyyyyEditor extends PropertyEditorSupport {
	
	private final Logger log = Logger.getLogger(CalendarddMMyyyyEditor.class);

	@Override
	public void setAsText(String data) throws IllegalArgumentException {
		if(data !=null && !data.equals("")){
			SimpleDateFormat sdf = new SimpleDateFormat("dd/MM/yyyy");
			try {
				Date dataNascimento = sdf.parse(data);
				Calendar calendar = new GregorianCalendar();
				calendar.setTime(dataNascimento);
				this.setValue(calendar);
				
			} catch (ParseException e) {
				
				log.error("Erro de formato de data invalido: " + e.getMessage());
			}
		}
	}

	@Override
	public String getAsText() {

		if(getValue() != null){
			return new SimpleDateFormat("dd/MM/yyyy").format(((Calendar) getValue()).getTime());
		}

		return "";
	}
}
