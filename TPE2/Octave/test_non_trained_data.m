function output = test_non_trained_data(perceptron, output, data_size, patterns, expected_outputs)
	data_percentage_size = round((perceptron.percentage * data_size) / 100);
	output.test_network_outputs = [];
    output.test_data_accuracy = 0;
    output.test_errors = [];
    output.test_ecm = 0;

	for testing_counter = data_percentage_size:data_size
		x1 = patterns(testing_counter,1);
		x2 = patterns(testing_counter,2);
		expected_z = expected_outputs(testing_counter);
		input_vector = [-1; x1 ; x2];
		[h,V] = forward_propagation(input_vector, perceptron.layers, output.w_network, perceptron.activation);
		output.test_network_outputs(testing_counter - data_percentage_size + 1) = V{perceptron.layers};
	end

	[output.test_errors, output.test_ecm, output.test_data_accuracy] = calculate_errors_and_accuracy(1, output.test_network_outputs, expected_outputs(data_percentage_size:data_size), perceptron.epsilon);

end