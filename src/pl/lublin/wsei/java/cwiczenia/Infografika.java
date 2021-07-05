package pl.lublin.wsei.java.cwiczenia;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class Infografika
{

    String tytul, adresStrony, szerokosc, wysokosc, adresGrafiki, adresMiniaturki;

    public Infografika(String text)
    {

        Pattern pat = Pattern.compile("<title><!\\[CDATA\\[(.*)\\]\\]");
        Matcher m = pat.matcher(text);

        if (m.find())
            tytul = m.group(1);
        else
            tytul = "";


        pat = Pattern.compile("<link>(.*)</link>");
        m = pat.matcher(text);

        if (m.find())
            adresStrony = m.group(1);
        else
            adresStrony = "";

        // <media:content url=\"(.*.jpg)
        // <media:content url="(.*)" t
        pat = Pattern.compile("<media:content url=\"(.*)\" t");
        m = pat.matcher(text);

        if (m.find())
            adresGrafiki = m.group(1);
        else
            adresGrafiki = "";


        // <media:thumbnail url=\"(.*.jpg)
        // <media:thumbnail url="(.*)"
        pat = Pattern.compile("<media:thumbnail url=\"(.*)\"");
        m = pat.matcher(text);

        if (m.find())
            adresMiniaturki = m.group(1);
        else
            adresMiniaturki = "";

        // height="(.*)"
        pat = Pattern.compile("height=\"(.*)\"");
        m = pat.matcher(text);

        if (m.find())
            wysokosc = m.group(1);
        else
            wysokosc = "";


        // width="(.*)" height
        pat = Pattern.compile("width=\"(.*)\" height");
        m = pat.matcher(text);

        if (m.find())
            szerokosc = m.group(1);
        else
            szerokosc = "";

    }
    public void print()
    {
        System.out.println("\nInfografika: ");
        System.out.println("\tTytul: " + tytul);
        System.out.println("\tAdres strony: " + adresStrony);
        System.out.println("\tAdres grafiki: " + adresGrafiki);
        System.out.println("\tAdres miniaturki: " + adresMiniaturki);
        System.out.println("\tRozmiary: " + szerokosc + "x" + wysokosc);
    }
}
