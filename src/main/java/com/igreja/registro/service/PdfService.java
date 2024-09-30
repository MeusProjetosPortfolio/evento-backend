package com.igreja.registro.service;

import com.igreja.registro.model.Person;
import com.itextpdf.text.Document;
import com.itextpdf.text.DocumentException;
import com.itextpdf.text.Element;
import com.itextpdf.text.Paragraph;
import com.itextpdf.text.pdf.PdfPCell;
import com.itextpdf.text.pdf.PdfPTable;
import com.itextpdf.text.pdf.PdfWriter;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.util.List;
@Service
public class PdfService {

    @Autowired
    private PersonService personService;

    public ByteArrayInputStream gerarRelatorioPdf(){
        List<Person> participantes = personService.listarTodos();
        Document document = new Document();
        ByteArrayOutputStream out = new ByteArrayOutputStream();
        try {
            PdfWriter.getInstance(document,out);
            document.open();

            Paragraph title = new Paragraph("Relatório de Participantes");
            title.setAlignment(Element.ALIGN_CENTER);
            document.add(title);
            document.add(new Paragraph(" "));  // Linha em branco

            // Criar uma tabela com 3 colunas para mostrar os participantes
            PdfPTable table = new PdfPTable(3);
            table.setWidthPercentage(100);
            table.setSpacingBefore(10f);
            table.setSpacingAfter(10f);

            // Definindo cabeçalhos das colunas
            PdfPCell header1 = new PdfPCell(new Paragraph("Nome"));
            PdfPCell header2 = new PdfPCell(new Paragraph("Comunidade"));
            PdfPCell header3 = new PdfPCell(new Paragraph("Contato"));

            table.addCell(header1);
            table.addCell(header2);
            table.addCell(header3);

            // Adiciona os dados de cada participante
            for (Person participante : participantes) {
                table.addCell(participante.getName());
                table.addCell(participante.getChurch());
                table.addCell(participante.getCellphone());
            }

            // Adicionar a tabela ao documento
            document.add(table);
            document.close();
        }catch (DocumentException e) {
            e.printStackTrace();
        }

        return new ByteArrayInputStream(out.toByteArray());
    }
}



