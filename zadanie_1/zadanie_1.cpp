#include <fstream>
#include <iostream>
#include <cstdlib>
#include <ctime>
#include <string>
using namespace std;


class Perceptron {
private:
	double x[26];
	double w[26];
	bool y;
	double suma;
	fstream plik;
	double n;
public:
	Perceptron() {
		n = 0.1;
		int a;
		suma = 0;
		plik.open("plik.txt", ios::in);
		if (plik.good()) cout << "otwarto plik" << endl;
		srand(time(NULL));
		for (int i = 0; i < 26; ++i) {
			a = (rand() % 11)+1;
			w[i] = (double)a/100 ;
		}
	}
	void wysw() {
		for (int i = 0; i < 26; ++i) {
			cout << w[i] << " ";
		}
		cout << endl;
	}
	double sigma() {
		suma = 0;
		for (int i = 0; i < 26; ++i) {
			suma += x[i] * w[i];
		}
		return suma;
	}
	bool f_aktywacji() {
		if (suma > 0) return true;
		else return false;
	}

	void learn() {
		//string str;
		if (plik.good()) {
			while (!plik.eof()) {
				for (int i = 0; i < 26; ++i) {
					plik >> x[i];
				}
				plik >> y;
				sigma();
				if (f_aktywacji() == 0 && y == 1) {
					for (int i = 0; i < 26; ++i) {
						w[i] = w[i] + n*x[i];
					}
				}
				if (f_aktywacji() == 1 && y == 0) {
					for (int i = 0; i < 26; ++i) {
						w[i] = w[i] - n*x[i];
					}
				}
				cout << "WAGI" << endl;
				for (int i = 0; i < 26; ++i) {
					cout << w[i] << " ";
				}
				cout << endl;
				

			}
		}
	}


	bool sprawdz(double we[26]) {
		for (int i = 0; i < 26; ++i) {
			x[i] = we[i];
		}
		sigma();
		if (f_aktywacji()) return true;
		else return false;


	}


};


int main() {
	fstream plik;
	Perceptron per;
	double tab[26];
	per.wysw();
	per.learn();
	int i = 0;
	do {
		cout << "chcesz wyjsc" << endl;
		cin >> i;
		plik.open("test.exe", ios::in);
		if (plik.good()) cout << "otwarto plik" << endl;

		for (int i = 0; i < 26; ++i) {
			plik >> tab[i];
		}
		plik.close();
		if (per.sprawdz(tab)) {
			cout << "tekst jest w jezyku angielskim" << endl;
		}
		else cout << "tekst jest w jezyku polskim" << endl;

	} while (i == 0);


	system("PAUSE");
	return 0;
}
