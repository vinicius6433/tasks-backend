package br.ce.wcaquino.taskbackend.utils;

import org.junit.Assert;
import org.junit.Test;

import java.time.LocalDate;

public class DateUtilsTest{

    @Test
    public void deveRetornarTrueParaDatasFuturas(){
        LocalDate date = LocalDate.of(2030, 04,10);
        Assert.assertTrue(DateUtils.isEqualOrFutureDate(date));
    }
    @Test
    public void deveRetornarFalseParaDatasFuturas(){
        LocalDate date = LocalDate.of(2010, 04,10);
        Assert.assertFalse(DateUtils.isEqualOrFutureDate(date));
    }
    @Test
    public void deveRetornarTrueParaDataAtual(){
        LocalDate date = LocalDate.now();
        Assert.assertTrue(DateUtils.isEqualOrFutureDate(date));
    }
}