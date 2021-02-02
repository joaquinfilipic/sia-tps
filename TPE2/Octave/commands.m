%-----------------------------
%---Initialization commands---
%-----------------------------

	%param: number of configuration file
perceptron = Perceptron('01');

[patterns, expected_outputs, x1_abs_max, x2_abs_max, z_abs_max] = load_normalized_data(perceptron.data_file);
data_size = size(patterns)(1);

	%random initial weights
[w_network, last_w_variation] = random_weights(perceptron.layers, perceptron.neurons_per_layer);

output = Output(w_network);

%-----------------------
%---Training commands---
%-----------------------

	%set epochs to be run
perceptron.epochs = 100;

	%train with given percentage of the data, with incremental or batch mode
output = train_incremental(perceptron, output, data_size, patterns, expected_outputs, w_network, last_w_variation, x1_abs_max, x2_abs_max, z_abs_max);
output = train_batch(perceptron, output, data_size, patterns, expected_outputs, w_network, last_w_variation);


%----------------------
%---Testing commands---
%----------------------

%data testing is needed to show testing metrics and obtained plots
output = test_all_data(perceptron, output, data_size, patterns, expected_outputs);
output = test_non_trained_data(perceptron, output, data_size, patterns, expected_outputs);


%--------------------------------
%---Metrics and Plots commands---
%--------------------------------

get_training_metrics(output);
get_testing_metrics(output);
plot_expected_and_obtained_outputs(patterns, expected_outputs, output, x1_abs_max, x2_abs_max, z_abs_max);
plot_expected_output(patterns, expected_outputs, x1_abs_max, x2_abs_max, z_abs_max);
plot_obtained_output(patterns, output, x1_abs_max, x2_abs_max, z_abs_max);
plot_eta_variation(output, perceptron);
plot_mse_variation(output);
