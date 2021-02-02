function get_training_metrics(output)
	printf('Epochs run: %d\n', output.epoch_count_index - 1);
	%printf('Saturation flag: %d\n', output.saturation);
	printf('Cut condition flag: %d\n', output.cut_condition);
	printf("Final Eta: %g\n", output.eta_record(columns(output.eta_record)));
	printf('MSE (last epoch): %g\n', output.ecm);
	printf('Trained data accuracy (last epoch): %g%%\n', output.train_data_accuracy * 100);
end