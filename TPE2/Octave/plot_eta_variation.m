function plot_eta_variation(output, perceptron)
	record_size = columns(output.eta_record);
	refresh();
	plot(1:perceptron.eta_check_steps:output.epoch_count_index - 1, output.eta_record(1:perceptron.eta_check_steps:record_size), "linewidth", 2);
	set(gca, "linewidth", 3, "fontsize", 12);
	title ("Training Factor");
	xlabel ("Epochs");
	ylabel ("Eta");
	legend ("Eta variation");
end