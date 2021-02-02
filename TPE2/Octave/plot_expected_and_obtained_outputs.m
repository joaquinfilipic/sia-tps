function plot_expected_and_obtained_outputs(patterns, expected_outputs, output, x1_abs_max, x2_abs_max, z_abs_max)
	net_size = columns(output.test_network_outputs);
	patterns_size = rows(patterns);
	
	figure(1);

	%plot3(patterns(patterns_size-net_size+1:patterns_size,1), patterns(patterns_size-net_size+1:patterns_size,2), output.test_network_outputs, '.', 'color', 'blue', "markersize", 12);
	%set(gca, "linewidth", 3, "fontsize", 12);
	%legend ("Generated terrain");

	x1 = patterns(patterns_size-net_size+1:patterns_size,1);
	x2 = patterns(patterns_size-net_size+1:patterns_size,2);
	z = output.test_network_outputs';
	ti = -2:.025:2;
	[X1,Y1] = meshgrid(ti,ti);
	Z1 = griddata(x1 .* x1_abs_max,x2 .* x2_abs_max,z .* z_abs_max,X1,Y1);
	mesh(X1,Y1,Z1);
	title ("Generated Terrain");

	%hold on
	
	figure(2);
	
	%plot3(patterns(:,1), patterns(:,2), expected_outputs', '.', 'color','red', "markersize", 12);
	
	xx1 = patterns(:,1);
	xx2 = patterns(:,2);
	zz = expected_outputs;
	tti = -2:.025:2;
	[XX1,YY1] = meshgrid(ti,ti);
	ZZ1 = griddata(xx1 .* x1_abs_max,xx2 .* x2_abs_max,zz .* z_abs_max,XX1,YY1);
	mesh(XX1,YY1,ZZ1);
	title ("Expected Terrain");

end