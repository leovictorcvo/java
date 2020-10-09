package br.com.leovictor.javautil;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.OutputStream;
import java.io.OutputStreamWriter;
import java.io.Reader;
import java.io.Writer;

public class copiaArquivo {

	public static void main(String[] args) {

		try (InputStream fis = new FileInputStream("lorem.txt");
				Reader isr = new InputStreamReader(fis);
				BufferedReader br = new BufferedReader(isr);
				OutputStream fos = new FileOutputStream("lorem2.txt");
				Writer osw = new OutputStreamWriter(fos);
				BufferedWriter bw = new BufferedWriter(osw);) {

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
