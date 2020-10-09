package br.com.leovictor.javautil;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.io.Reader;
import java.io.Writer;

public class copiaArquivoFileWriterFileReader {

	public static void main(String[] args) {

		try (Reader fr = new FileReader("lorem.txt");
				BufferedReader br = new BufferedReader(fr);
				Writer fw = new FileWriter("lorem2.txt");
				BufferedWriter bw = new BufferedWriter(fw);) {

			String linha = br.readLine();
			while (linha != null) {
				bw.write(linha);
				linha = br.readLine();
				if (linha != null) {
					bw.newLine();					
				}
			}

		} catch (IOException e) {
			System.out.println("Erro ao ler o arquivo: " + e.getMessage());
		}
	}

}
