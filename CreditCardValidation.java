import java.io.File;
import java.util.Scanner;

/**
 * CreditCardValidation - Validates dates and Converts it to a specific format.
 * 
 * @author - Joao Zampa
 * @version - JZ_03
 * 
 * Assignment: #4
 * Course: ADEV-1003
 * Section :  
 * Date Created: 10.06
 * Last Update:  10.06
 */

public class CreditCardValidation
{
    public static void main(String[] args) throws Exception
    {   
        String cardNumberFromFile,
        cardName,
        subString;

        int numberOfValidMaster     = 0,
            numberOfinvalidMaster   = 0,
            numberOfValidVisa       = 0,
            numberOfinvalidVisa     = 0,
            numberOfValidAmex       = 0,
            numberOfinvalidAmex     = 0,
            totalOfRejectedCards    = 0,
            numberOfCardsProcessed  = 0,
            numberLength            = 0,
            digitAsNumber           = 0,
            charPosition            = - 1,
            digitsSum               = 0,
            position                = 0,
            digitAsANumber          = 0;

        final String MASTERCARD    = "Mastercard",
              VISA                 = "Visa",
              AMERICAN_EXPRESS     = "American Express";

        // Starting File
        File creditCardNumbersFile = new File("creditcardnumbers.txt");
        
        if(creditCardNumbersFile.exists() == true)
        {
            // Scaning file
            Scanner creditCardNumberInput = new Scanner(creditCardNumbersFile);
        
        
        
            // Condition to do the loop 
            while (creditCardNumberInput.hasNext()) 
            {
                // Creating a variable to number got fromfile
                cardNumberFromFile = creditCardNumberInput.nextLine ();
                
                // Reseting position to zero
                position = 0;
                
                // Reseting digitSum to zero
                digitsSum = 0;
                
                // If the number do not starts with 3, 4 or 5 it will be invalid ad will be add 1 to total rejected
                if(cardNumberFromFile.charAt(0) != '3' && cardNumberFromFile.charAt(0) != '4' && cardNumberFromFile.charAt(0) != '5')
                {
                    totalOfRejectedCards = totalOfRejectedCards + 1;
                }
    
                // If it has  a valid starter number - checking for masters - Starting with 5
                else if(cardNumberFromFile.charAt(0) == '5' )
                {
                    // If starts with starts with 5 and next number is not in the valid rang, reject the card and add 1 to total rejected
                    if(cardNumberFromFile.charAt(1) != '1' && cardNumberFromFile.charAt(1) != '2' && cardNumberFromFile.charAt(1) != '3' && cardNumberFromFile.charAt(1) != '4' && cardNumberFromFile.charAt(1) != '5')
                    {
                        totalOfRejectedCards ++;
                    }   
                    
                    // If still not rejected, check for the length - if not in the length it is a invalid
                    else if (cardNumberFromFile.length() < 16 || cardNumberFromFile.length() > 19)
                    {
                        numberOfinvalidMaster ++;
                    }
                    
                    // If it still a valid card go thru the Luhn - the first one will be commented.
                    else
                    {  
                        // Geting the length of the card and storing it intoa variable
                        numberLength = cardNumberFromFile.length();
                        
                        // Geting the number less the last digit
                        subString = (cardNumberFromFile.substring(0, numberLength - 1));    
                        
                        // Making the conditin to read each number
                        for(int i = subString.length() - 1; i >= 0; i--)
                        //while(numberLength > 0)
                        {
                            // Criating a variable as position  so while it reads backwards the position will be stored as 1 , 2 , 3.....
                            position ++;
    
                            // Storing at a variable each number read backwards
                            digitAsANumber = (Character.getNumericValue(subString.charAt(i)));
    
                            // If position is Odd multiply by 2
                            if(position % 2 != 0)
                            {
                                digitAsANumber = digitAsANumber * 2;
                            }
                            
                            // If odd multiplied by 2 is bigger then 9 take 9 out, else it is even and just store as it is
                            if(digitAsANumber > 9)
                            {
                                digitAsANumber = digitAsANumber - 9;
                            }
                                                    
                            // Making the sum and storing it into a variable
                            digitsSum = digitsSum + digitAsANumber;                         
                        }
                        
                        // If the luhn returns the module diferent than the last digit it will be a especific card rejected and will be add to the variable to rejected card for the card that is been read / else it is going to be a valid card
                        if (digitsSum % 10 != Character.getNumericValue(cardNumberFromFile.charAt(numberLength - 1)))
                        {
                            System.out.printf("%s has an invalid check digit.\n", cardNumberFromFile);
    
                            numberOfinvalidMaster ++;                       
                        }
                        else
                        {
                            System.out.printf("%s is a valid %s number.\n", cardNumberFromFile, MASTERCARD);
    
                            numberOfValidMaster ++; 
                        }                  
                    }
                } 
                
                // Validating Visa - if it doesn't starts with 4 is rejected so is added 1 to total rejected
                else if(cardNumberFromFile.charAt(0) == '4' )
                {
                    // If still not rejected, check for the length - if not in the length it is a invalid
                    if(cardNumberFromFile.length() < 13 || cardNumberFromFile.length() > 16)
                    {
                        System.out.printf("%s has an invalid number of digits for the credit card type.\n", cardNumberFromFile);
    
                        numberOfinvalidVisa ++;
                    } 
                    
                    // If still no error check for the luhn                    
                    else
                    {   
                        numberLength = cardNumberFromFile.length();      
    
                        subString = (cardNumberFromFile.substring(0, numberLength - 1));     
    
                        for(int i = subString.length() - 1; i >= 0; i--)
                        
                        {
                            position ++;
                            
                            digitAsANumber = (Character.getNumericValue(subString.charAt(i)));
                            
                            if(position % 2 != 0)
                            {
                                digitAsANumber = digitAsANumber* 2;
                            }
    
                            if(digitAsANumber > 9)
                            {
                                digitAsANumber = digitAsANumber - 9;
                            }
                            
                            digitsSum = digitsSum + digitAsANumber;                         
                        }
    
                        if (digitsSum % 10 != Character.getNumericValue(cardNumberFromFile.charAt(numberLength - 1)))
                        {
                            System.out.printf("%s has an invalid check digit.\n", cardNumberFromFile);
                            
                            numberOfinvalidVisa ++;                       
                        }
                        else
                        {
                            System.out.printf("%s is a valid %s number.\n", cardNumberFromFile, VISA);
    
                            numberOfValidVisa ++; 
                        }                                           
                    }                   
                }
    
                // Checking for AMEX/ if it doesn't starts with 5 and 4 it will be 3 then check for the next nuymber if starts with 4 or 7
                else 
                {
                    if(cardNumberFromFile.charAt(1) != '4' && cardNumberFromFile.charAt(1) != '7')
                    {
                        totalOfRejectedCards ++;
                    }   
                    
                    // If still not rejected, check for the length - if not in the length it is a invalid
                    else if (cardNumberFromFile.length() != 15)
                    {
                        numberOfinvalidAmex ++;
                    }
                    
                    // If there is no erro so far, check for the luhn 
                    else
                    {      
                        numberLength = cardNumberFromFile.length();      
    
                        subString = (cardNumberFromFile.substring(0, numberLength - 1));     
    
                        for(int i = subString.length() - 1; i >= 0; i--)
                        {
                            position ++;
                            
                            digitAsANumber = (Character.getNumericValue(subString.charAt(i)));
                            
                            if(position % 2 != 0)
                            {
                                digitAsANumber = digitAsANumber* 2;
                            }
    
                            if(digitAsANumber > 9)
                            {
                                digitAsANumber = digitAsANumber - 9;
                            }
                            
                            digitsSum = digitsSum + digitAsANumber;                         
                        }
    
                        if (digitsSum % 10 != Character.getNumericValue(cardNumberFromFile.charAt(numberLength - 1)))
                        {
                            System.out.printf("%s has an invalid check digit.\n", cardNumberFromFile);
    
                            numberOfinvalidAmex ++;                       
                        }
                        else
                        {
                            System.out.printf("%s is a valid %s number.\n", cardNumberFromFile, AMERICAN_EXPRESS);
    
                            numberOfValidAmex ++; 
                        }                                          
                    }
                }       
            }        
            // Printing to the Screen the summary
            System.out.printf("\n***\n\n%-18s %5d\n", "Records processed:", numberOfCardsProcessed = numberOfValidMaster + numberOfinvalidMaster + numberOfValidVisa + numberOfinvalidVisa + numberOfValidAmex + numberOfinvalidAmex + totalOfRejectedCards);        
            System.out.println("========================");
            System.out.printf("%-18s %5d (%d)\n", "Mastercard:", numberOfValidMaster, numberOfinvalidMaster);
            System.out.printf("%-18s %5d (%d)\n", "Visa:", numberOfValidVisa, numberOfinvalidVisa);
            System.out.printf("%-18s %5d (%d)\n", "American Express:", numberOfValidAmex, numberOfinvalidAmex);
            System.out.printf("\n%-18s %5d", "Rejected:", totalOfRejectedCards); 
        }   
        
        else
        {
            System.out.println("*** creditcardnumbers.txt does not exist. ***");
        }
    }
}


