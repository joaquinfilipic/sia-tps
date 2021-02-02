function get_testing_metrics(output)
	printf('MSE: %g\n', output.test_ecm);
	printf('Tested data accuracy: %g%%\n', output.test_data_accuracy * 100);
end