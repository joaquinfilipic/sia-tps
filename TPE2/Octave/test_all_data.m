function output = test_all_data(perceptron, output, data_size, patterns, expected_outputs)
	output.test_network_outputs = [];
    output.test_data_accuracy = 0;
    output.test_errors = [];
    output.test_ecm = 0;

	for testing_counter = 1:data_size
		x1 = patterns(testing_counter,1);
		x2 = patterns(testing_counter,2);
		expected_z = expected_outputs(testing_counter);
		input_vector = [-1; x1 ; x2];
		[h,V] = forward_propagation(input_vector, perceptron.layers, output.w_network, perceptron.activation);
		output.test_network_outputs(testing_counter) = V{perceptron.layers};
	end

	[output.test_errors, output.test_ecm, output.test_data_accuracy] = calculate_errors_and_accuracy(1, output.test_network_outputs, expected_outputs, perceptron.epsilon);

end