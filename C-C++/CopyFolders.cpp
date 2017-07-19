#include <iostream>
#include <cstdlib>
#include <sys/types.h>
#include <dirent.h>
#include <unistd.h>
#include <cstring>
#include <string>
#include <vector>
#include <fcntl.h>
#include <sys/wait.h>

using namespace std;

void copyDir(string sourceDir, string targetDir);
void copyFile(string source, string target);

int main(int argc, char *argv[]) {
    if (argc != 3) {
        cerr << "Please use correct format: copyFile sourcePath targetPath"
                << endl;
        return -1;
    }
    copyDir(string(argv[1]), string(argv[2]));
    return 0;

}


// help to copy all files to new folder

void copyDir(string sourcePath, string targetPath) {
    DIR *dir;
    struct dirent *info;
    vector<int> children;

    dir = opendir(sourcePath.c_str());

    if (dir == NULL) {
        cerr << "ERROR" << endl;
        exit(-1);
    }

    info = readdir(dir);
    while (info != NULL) {
        string name(info ->d_name);
        if (info->d_type == DT_REG) {
            int myPipe[2];
            char sp[256];
            char tp[256];
            pipe(myPipe);
            int pidChild = fork();

            if (pidChild == 0) {
                close(myPipe[1]);
                read(myPipe[1], sp, 256 * sizeof (char));
                read(myPipe[1], tp, 256 * sizeof (char));
                close(myPipe[0]);
                copyFile(string(sp), string(tp));
                return;
            } else {
                children.push_back(pidChild);
                close(myPipe[0]);
                string s = sourcePath + "/" + name;
                string t = targetPath + "/" + name;
                strncpy(sp, s.c_str(), 256);
                strncpy(tp, t.c_str(), 256);
                write(myPipe[1], sp, 256 * sizeof (char));
                write(myPipe[1], tp, 256 * sizeof (char));
                close(myPipe[1]);
            }
        }
        info = readdir(dir);
    }
    closedir(dir);
    for (int i=0; i<children.size(); ++i) {
        waitpid(children[i], NULL, WNOHANG);
    }

}

// help to copy single file to new file

void copyFile(string source, string target) {
    int in, out;
    unsigned char byte;
    cout << "copyFile" << source << " " << target << endl;

    in = open(source.c_str(), O_RDONLY);
    out = open(target.c_str(), O_WRONLY | O_CREAT | O_TRUNC);

    while (read(in, &byte, sizeof (unsigned char))) {
        write(out, &byte, sizeof (unsigned char));
    }

}
