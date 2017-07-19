#include <cstdlib>
#include <iostream>
#include <sys/types.h>
#include <dirent.h>
#include <string>
#include <cstring>
#include <unistd.h>

using namespace std;

void dir(string path, int tab);
void TAB(int n);
DIR *opendir(const char *name);
int closedir(DIR *dirp);
struct dirent *readdir(DIR *dirp);

/*
 *
 */
int main(int argc, char** argv) {
    char path[256];
    if (argc==2) {
        strcpy(path, argv[1]);
    }
    else {
        getcwd(path,256);
    }

    dir(string(path), 0);

    return 0;
}

void dir(string path, int tab) {

    DIR *directory;
    directory = opendir(path.c_str());
    struct dirent *entry;
    entry = readdir(directory);

    while (entry != NULL) {
        string name = string(entry->d_name);
        TAB(tab);
        cout << name << endl;
        if (entry->d_type == DT_DIR && name != "."
                && name != "..") {
            dir(path+"/"+name, tab +2);

        }

        entry = readdir(directory);


    }
    closedir(directory);

}

void TAB(int n) {
    for (int i = 0; i < n; i++) {
        cout << "\t";
    }

}
