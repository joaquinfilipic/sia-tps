function output = train_batch(perceptron, output, data_size, patterns, expected_outputs, w_network, last_w_variation)	
	output.cut_condition = 0;
	output.saturation = 0;

	%percentage
	data_percentage_size = round((perceptron.percentage * data_size) / 100);

	for ep = 1:perceptron.epochs
		output.saturation = 0;
		deltas = {};
		%training
		for training_counter = 1:data_percentage_size
			x1 = patterns(training_counter,1);
			x2 = patterns(training_counter,2);
			expected_z = expected_outputs(training_counter);
			input_vector = [-1; x1 ; x2];	

			[h,V] = forward_propagation(input_vector, perceptron.layers, output.w_network, perceptron.activation);

			%check saturation
			#{
			for i=1:perceptron.layers
				for j = 1:size(h{i})(1)
					if h{i}(j,1) > perceptron.saturation_value
						output.saturation = 1;
					end
				end
			end

			if output.saturation == 1
				break;
			end
			#}

			output.network_outputs(training_counter) = V{perceptron.layers};

			if(training_counter == 1)
				deltas = calculate_deltas(perceptron.layers, perceptron.activation_d, h, V, expected_z, output.w_network, perceptron.neurons_per_layer);	
			else
				new_deltas = calculate_deltas(perceptron.layers, perceptron.activation_d, h, V, expected_z, output.w_network, perceptron.neurons_per_layer);
				for d = 1:columns(new_deltas)
					deltas{d} += new_deltas{d};
				end
			end
		end

		[output.w_network, last_w_variation] = backward_propagation(input_vector, output.w_network, perceptron.neurons_per_layer, perceptron.eta, deltas, perceptron.layers, V, last_w_variation, perceptron.momentum_enabled, perceptron.alpha);

		%check saturation
		if output.saturation == 1
			break;
		else
			[output.errors, output.ecm, output.train_data_accuracy] = calculate_errors_and_accuracy(1, output.network_outputs, expected_outputs, perceptron.epsilon);
			output.ecm_record(output.epoch_count_index) = output.ecm;

			%printf('MSE: %g\n', output.ecm);

			%set only on first epoch
			if ep == 1
				old_ecm = output.ecm;
				old_w_network = output.w_network;
			end

			%check cut condition
			if output.ecm < perceptron.cut_condition_value
				output.cut_condition = 1;
				output.epoch_count_index++;
				break;
			end

			output.eta_record(output.epoch_count_index) = perceptron.eta;

			%adaptative eta
			if perceptron.adaptative_eta_enabled == 1 && rem(ep,perceptron.eta_check_steps) == 0
				if output.ecm < old_ecm
					perceptron.eta += perceptron.eta_increment;
					perceptron.momentum_enabled = 1;
					old_w_network = output.w_network;
				elseif output.ecm > old_ecm
					perceptron.eta -= perceptron.eta_decrement * perceptron.eta;
					perceptron.momentum_enabled = 0;
					output.w_network = old_w_network;
				end
				old_ecm = output.ecm;
			end

		end
		output.epoch_count_index++;
	end
end