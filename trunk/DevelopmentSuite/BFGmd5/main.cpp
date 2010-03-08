#include "math.h"
#include "md5wrapper.h"

#include <cstdlib>
#include <iostream>
#include <string>
#include <fstream>


using namespace std;

int totalcomFunc(int passwordSize,int arraySize);
string funtionJump(double val,int arraySize,char arrayCharactor[],int passSize);

int main(int argc, char** argv)
{
    if(argc != 9)
    {
        cout << "-2" << endl;
        exit(0);
    }
    bool Symbolic = (argv[1][0] == '1');
    bool Numeric = (argv[2][0] == '1');
    bool Upper = (argv[3][0] == '1');
    bool Lower = (argv[4][0] == '1');
    int passLen = atoi(argv[5]); // password lenth
    int segsize = atoi(argv[6]); // the segmentsize
    int segNo = atoi(argv[7]); // the segment no
    string passhash = argv[8]; //the hash of the real password
   
    int size = 0;
    int index= 0;
      
    if(Symbolic)
    {
        size = size + 32;
    }
    if(Numeric)
    {
        size = size + 10;
    }
    if(Upper)
    {
        size = size + 26;
    }
    if(Lower)
    {
        size = size + 26;
    }

    //-----------------------------------------------------------
    char myMap[size]; // creation of the array
       
    //assigning ------------------------------------------------------------------
       
    if(Symbolic)
    {
        char first = '!';
        for(int i = 0 ; i < 15 ; i++)
        {
            myMap[index] = first +i;
            index++ ;
        }

        for(int i = 25 ; i < 32 ; i++)
        {
            myMap[index] = first +i;
            index++ ;
        }
        for(int i = 58 ; i < 64 ; i++)
        {
            myMap[index] = first +i;
            index++ ;
        }
        for(int i = 90 ; i < 94 ; i++)
        {
            myMap[index] = first +i;
            index++ ;
        }
   }

   if(Numeric)
   {
        char first = '0';
        for(int i = 0 ; i < 10 ; i++)
        {
            myMap[index] = first +i;
            index++ ;
        }
   }

   if(Upper)
   {
        char first = 'A';
        for(int i = 0 ; i < 26 ; i++)
        {
            myMap[index] = first +i;
            index++ ;
        }
    }

    if(Lower)
    {
        char first = 'a';

        for(int i = 0 ; i < 26 ; i++)
        {
            myMap[index] = first +i;
            index++ ;
        }
    }

    //------------------------map creation done-----------------------------------
    string final = "";
    //**************************************************************************************

    int start = segsize * segNo;
    int end = segsize * (segNo + 1);

    string com = "";
    string hash ="";
    md5wrapper md5;

    for(int i=start+1; i<end+1; i++)
    {
        com=funtionJump(i,size,myMap,passLen);
        //This is whr the hash is put----
	hash=md5.getHashFromString(com);
       
	if(hash == passhash)
        {
            final = com;
            cout << "1 " << final <<endl;
            break;
        }
    }
    cout << "0" << endl;
    exit(0);
}

string funtionJump(double val,int arraySize,char arrayCharactor[],int passSize)
{
    int i=0;//array index 
    double reminder=0;//reminder
    int z = 0;//password size
    long double lowLevel=0,highLevel=passSize;//password size changing levels

    if(val<=0)//error
    {
        cout << "-1" << endl;
        exit(0);
    }

    while(val>highLevel)//find how many caractor are used in the combination
    {
        double ans = (pow((double)arraySize,z));
        lowLevel=(arraySize*(ans-1))/(arraySize-1);//find the low level of no of caractors
		
        z++;//increment no of password charactors
		
        ans = (pow((double)arraySize,z));
		
        highLevel=(arraySize*(ans-1))/(arraySize-1);//find the high level of no of caractors
    }
    val= val-(lowLevel+1);//sequecne no of combination reset
    char arrayCombinationString[(int)z];//array that store the combination
    int z2=0;
    z2=z;
    int y=z-1;//array index
    
    while(z!=0)//covert the value to the array_size base no
    {
        reminder=fmod(val,arraySize);//get the reminder
        arrayCombinationString[y]=arrayCharactor[(long)reminder];//store charactor fit to reminder
        val=val-reminder;//can't pass val to next equation with decimal numbers
        val=(val/arraySize);//ready the val to next loop
        z--;//decrement the point on no of caractors
        y--;//increment the combinaton array index
    }

    string word(arrayCombinationString,z2);
    return word;
}

