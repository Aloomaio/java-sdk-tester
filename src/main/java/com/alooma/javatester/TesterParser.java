package com.alooma.javatester;

import com.google.devtools.common.options.Option;
import com.google.devtools.common.options.OptionsBase;
import java.lang.String;

/**
 * Created by yonatankiron on 19/03/2017.
 */
public class TesterParser extends OptionsBase {

    @Option(
            name = "help",
            abbrev = 'h',
            help = "Prints usage info.",
            defaultValue = "true"
    )
    public boolean help;

    @Option(
            name = "hostname",
            abbrev = 'o',
            help = "The server host.",
            category = "startup",
            defaultValue = ""
    )
    public String host;

    @Option(
            name = "port",
            abbrev = 'p',
            help = "The server port.",
            category = "startup",
            defaultValue = "5001"
    )
    public String port;

    @Option(
            name = "num",
            abbrev = 'n',
            help = "Number of messages.",
            category = "startup",
            defaultValue = "0"
    )
    public int numOfEvents;

    @Option(
            name = "protocol",
            help = "Which protocol to use.",
            category = "startup",
            defaultValue = "SSL"
    )
    public String protocol;

    @Option(
            name = "token",
            abbrev = 't',
            help = "Token.",
            category = "startup",
            defaultValue = ""
    )
    public String token;
}