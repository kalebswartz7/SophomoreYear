
#include <cstdlib>
#include <iostream>
#include <fstream>

using namespace std;

struct Item;

int exercise1();
int exercise2();
int exercise3();
int readItem(FILE *fp, struct Item *item);
  struct  Item {
        int codebar;
        char name[80];
        double price;
    };

int main(int argc, char** argv) {
    //exercise1();
    //exercise2();
    exercise3();

    return 0;
}
int exercise1() {
    string fileName, line;
    char check;
    int lowerCase = 0;
    int upperCase = 0;
    int digits = 0;
    int totChar = 0;
    cout << "Please enter a fileName to be read: ";
    cin >> fileName;

    ifstream input(fileName.c_str());
    if (input.is_open()) {
        while (getline(input, line)) {
            for (int i = 0; i < line.length(); i++) {
                totChar++;
                check = line.at(i);
                if (check >= 65 && check <= 90) {
                    upperCase++;

                }
                else if (check >= 97 && check <= 122) {
                    lowerCase++;

                }
                else if (check >= 48 && check <= 57) {
                    digits++;
                }
            }


        }
        input.close();
    }
    else cout << "Unable to open file\n";



    cout <<  fileName << " has:\n\t" << upperCase << " upper case letters\n\t"
            << lowerCase << " lower case letters\n\t"
            << digits << " digits\n\tand " << totChar << " total characters";
    return 0;
}

int exercise2() {
    string phrase;
    string newPhrase;
    string checkPhrase;
    char a;
    cout << "Enter your phrase or word: ";
    getline(cin, phrase);
    for (int i = 0; i < phrase.length(); i++) {
        a = phrase.at(i);
        if (a >= 65 && a <= 90) {
            newPhrase += tolower(a);
        }
        else if (a >= 97 && a <= 122) {
            newPhrase += a;
        }
    }
    for (int j = newPhrase.length(); j > 0; j--) {
        a = newPhrase.at(j-1);
        checkPhrase += a;
    }
    if (newPhrase == checkPhrase) {
        cout << phrase << " is a palindrome";
    }
    else {
        cout << phrase << " is not a palindrome";
    }



}



int exercise3() {

    FILE * inputFile;
    inputFile = fopen("bill.txt","r");
    Item newItem;
    int a = readItem(inputFile, &newItem);

}

int readItem(FILE *fp, struct Item *item) {
/* This function read one item from fp.
 *  It returns 1 when the item has been read or 0 if EOF has got it */



    return 0;
}
