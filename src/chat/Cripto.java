/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package chat;
import java.math.BigInteger;

public class Cripto {
   

    private BigInteger n, d, e;
    private BigInteger p = new BigInteger("91263102798060695100405277705174556838366893119746062968168692475964765163776253288517956243281653553276654629531783766120968200336516742919192725638577533304019873118156713182717373546121512275643103722092924721391546070476392980266585831487462257922956858505579194467325447518679360945056959237077962591161");
    private BigInteger q = new BigInteger("127555495921614444302400305604925437003724767893861369406667186991794385329668640517459080133120357889482623686024446667618450719303180072756546023177970596755333908675212469287464076777222448030318813673607261020489070807466320097772328381154524372060408883842275828437035892947952594520066258654479618611459");
    private int bitlen = 2048;
    
    public Cripto(){
        n = p.multiply(q);
        BigInteger m = (p.subtract(BigInteger.ONE)).multiply(q.subtract(BigInteger.ONE));
        e = new BigInteger("3");
        while(m.gcd(e).intValue() > 1) 
            e = e.add(new BigInteger("2"));
        
        d = e.modInverse(m);
    }
    public String decifrar(String mensagem){
        String mensagemDecifrada = new String(new BigInteger(mensagem).modPow(d, n).toByteArray());
        return mensagemDecifrada;
    }
    
    public String cifrar(String mensagem){
        String mensagemCifrada = new BigInteger(mensagem.getBytes()).modPow(e, n).toString();        
        return mensagemCifrada;
    }
}
    