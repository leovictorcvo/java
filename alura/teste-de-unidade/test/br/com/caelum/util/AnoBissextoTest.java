package br.com.caelum.util;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertTrue;

import org.junit.Test;

public class AnoBissextoTest {
	@Test
	public void deveRetornarAnoBissexto() {        
	    assertTrue(AnoBissexto.isAnoBissexto(2016));
	    assertTrue(AnoBissexto.isAnoBissexto(2012));
	}

	@Test
	public void naoDeveRetornarAnoBissexto() {        
	    assertFalse(AnoBissexto.isAnoBissexto(2015));
	    assertFalse(AnoBissexto.isAnoBissexto(2011));
	}
}
