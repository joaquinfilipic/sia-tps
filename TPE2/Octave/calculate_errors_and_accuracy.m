function [errors, ecm, accuracy] = calculate_errors_and_accuracy(index, network_outputs, expected_outputs, epsilon)
	ecm = 0;
	accuracy = 0;
	net_size = columns(network_outputs);
	for r = index:net_size
		errors(r) = (network_outputs(r) - expected_outputs(r))**2;
		ecm = ecm + errors(r);
		if abs(network_outputs(r) - expected_outputs(r)) < epsilon
			accuracy++;
		end
	end
	ecm = ecm / net_size;
	accuracy = accuracy / (net_size - index + 1); 
end