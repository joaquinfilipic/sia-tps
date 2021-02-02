function plot_obtained_output(patterns, output, x1_abs_max, x2_abs_max, z_abs_max)
	
	net_size = columns(output.test_network_outputs);
	patterns_size = rows(patterns);
	
	%plot3(patterns(patterns_size-net_size+1:patterns_size,1), patterns(patterns_size-net_size+1:patterns_size,2), output.test_network_outputs, '.', 'color', 'blue', "markersize", 12);
	%set(gca, "linewidth", 3, "fontsize", 12);
	%legend ("Generated terrain");
	refresh();
	x1 = patterns(patterns_size-net_size+1:patterns_size,1);
	x2 = patterns(patterns_size-net_size+1:patterns_size,2);
	z = output.test_network_outputs';
	ti = -2:.025:2;
	[X1,Y1] = meshgrid(ti,ti);
	Z1 = griddata(x1 .* x1_abs_max,x2 .* x2_abs_max,z .* z_abs_max,X1,Y1);
	mesh(X1,Y1,Z1);
	title ("Generated Terrain");

end