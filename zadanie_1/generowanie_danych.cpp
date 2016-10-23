#include <vector>
#include <fstream>
#include <iostream>
#include <ctype.h>
#include<string>
void error(const	char *	p, const	char	*	p2 = "")
{
	std::cerr << p << ' ' << p2 << std::endl;
	std::exit(1);
}
void generate_frequencies(const	char	*	filename, float	*frequencies)
{
	std::ifstream	infile(filename);
	if (!infile) error("Cannot open an input file", filename);
	std::vector<unsigned int>letter_count(26, 0);
	unsigned int num_characters = 0;
	char c;
	while (infile.get(c)) {
		c = tolower(c);
		if (c == 'ą') c = 'a';
		if (c == 'ć') c = 'c';
		if (c == 'ę') c = 'e';
		if (c == 'ł') c = 'l';
		if (c == 'ń') c = 'n';
		if (c == 'ó') c = 'o';
		if (c == 'ś') c = 's';
		if (c == 'ź') c = 'z';
		if (c == 'ż') c = 'z';
		if (c >= 'a'&&c <= 'z') {
			letter_count[c - 'a']++;
			num_characters++;
		}
	}
	if (!infile.eof()) error("Something strange happened");
	for (unsigned int i = 0; i != 26; i++) {
		frequencies[i] = letter_count[i] / (double)num_characters;
	}
}
int main(int argc, char * argv[]) {
	std::string a;
	std::fstream plik("plik.txt", std::ios::app);
	if (argc != 2) error("Remember to specify an input file");
	float frequencies[26];
	generate_frequencies(argv[1], frequencies);
	for (unsigned int i = 0; i != 26; i++) {
		//std::cout << frequencies[i] << ' ';
		plik << frequencies[i] << ' ';
	}
	int b = 0;
	std::cin >> b;
	plik << b;
	plik << std::endl;
	plik.close();
	std::cout << std::endl;
	return 0;
	
}
