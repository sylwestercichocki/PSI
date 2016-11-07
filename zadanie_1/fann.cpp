#include "fann.h"
#include<iostream>
using namespace std;
int main(){
	unsigned int layers[2] = { 26,1 };
	struct fann	*ann= fann_create_standard_array(2,layers);
	fann_train_on_file(ann,"plik.data",200,1,0.00001);
	fann_save(ann,"language_classify.net");
	fann_destroy(ann);



	struct fann *an1 = fann_create_from_file("language_classify.net");

	float frequencies[26] = { 0.0970149 ,0.0199005 ,0.0410448 ,0.0273632 ,0.0995025 ,0.00373134 ,0.0124378 ,0.00870647 ,0.0957711 ,0.0273632 ,0.0360697 ,0.039801 ,0.0298507 ,0.0597015 ,0.0771144 ,0.0199005 ,0 ,0.0460199 ,0.050995 ,0.0348259 ,0.0310945 ,0 ,0.0385572 ,0 ,0.0323383 ,0.0708955 };

	float *output = fann_run(ann, frequencies);
	std::cout << *output << endl;
	system("PAUSE");
	return 0;
}
