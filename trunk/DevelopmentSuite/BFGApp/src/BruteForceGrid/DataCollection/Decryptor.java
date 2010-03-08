package BruteForceGrid.DataCollection;

/**
 * entry classwhich may contain the value of the decryptor discription
 * 
 * @author Faraj
 */
public class Decryptor
{
    public static final String DELEMETER = " ";
    private int decryptorID;
    private String discription;
    public static int count = 0;

    /**
     * constructor contain the decrptor of the decryptor
     * 
     * @param discription decription of the decryptor
     * @see String
     */
    
    public Decryptor(String discription) {
        this.decryptorID = count++;
        this.discription = discription;
    }

    /**
     * use to get the discrptor of the decryptor
     * 
     * @return the descrptor of the decryptor
     * @see String
     */
    public String getDiscription()
    {
        return discription;
    }

}
