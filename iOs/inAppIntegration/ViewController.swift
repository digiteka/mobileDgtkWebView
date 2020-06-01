//
//  ViewController.swift
//  inAppIntegration
//
//  Created by Digiteka on 03/07/2019.
//  Copyright © 2019 Digiteka. All rights reserved.
//

import UIKit
import WebKit

class ViewController: UIViewController, UITextFieldDelegate, WKUIDelegate, UIApplicationDelegate, WKNavigationDelegate {

    @IBOutlet weak var webViewContainer: UIView!
    @IBOutlet weak var backButton: UIButton!
    @IBOutlet weak var nextButton: UIButton!
    @IBOutlet weak var playButton: UIButton!
    @IBOutlet weak var pauseButton: UIButton!
    @IBOutlet weak var stopButton: UIButton!
    @IBOutlet weak var reloadButton: UIButton!
    @IBOutlet weak var muteButton: UIButton!
    @IBOutlet weak var unmuteButton: UIButton!
    @IBOutlet weak var urlTextField: UITextField!
    
    // SURTOUT PAS DE PARAMETRE AUTOPLAY DANS L'URL !!!!
    let urlString:String = "https://www.ultimedia.com/deliver/generic/iframe?mdtk=01499695&width=722&height=406&zone=99&src=ppvsmm&type_player=0&sendstats=0&visible=&tagparam=&tagparamdecoded=&gdprconsentstring=&urlfacebook=https%3A%2F%2Fwww.lavoixdunord.fr%2F700994%2Farticle%2F2020-01-27%2Fpatrick-balkany-reste-en-prison-isabelle-balkany-est-folle-de-rage&ad=1&title=Ils%20se%20baignent%20tous%20les%20jours%20au%20Touquet%2C%20m%C3%AAme%20l%27hiver&endMessage=um_ultimedia_wrapper_ultimediaEndRoll&widgetPrefix=um_ultimedia_wrapper_&plindex=0&fstart=0"
    
    var webView: WKWebView!
    
    override func viewDidLoad() {
        super.viewDidLoad()
        // Do any additional setup after loading the view.
        
        let customFrame = CGRect.init(origin: CGPoint.zero, size: CGSize.init(width: 0.0, height: self.webViewContainer.frame.size.height))
        
        let webViewConfiguration = WKWebViewConfiguration()
        
        if #available(iOS 10.0, *) {
            webViewConfiguration.mediaTypesRequiringUserActionForPlayback = []
        } else {
            webViewConfiguration.requiresUserActionForMediaPlayback = false
        }
        webViewConfiguration.allowsInlineMediaPlayback = true
        webViewConfiguration.preferences.javaScriptCanOpenWindowsAutomatically = true
        webViewConfiguration.preferences.javaScriptEnabled = true
        
        self.webView = WKWebView (frame: customFrame , configuration: webViewConfiguration)
        
        webView.translatesAutoresizingMaskIntoConstraints = false
        webView.isOpaque = false
        webView.backgroundColor = UIColor.clear
        webView.scrollView.backgroundColor = UIColor.clear
        
        self.webViewContainer.addSubview(webView)
        webView.topAnchor.constraint(equalTo: webViewContainer.topAnchor).isActive = true
        webView.rightAnchor.constraint(equalTo: webViewContainer.rightAnchor).isActive = true
        webView.leftAnchor.constraint(equalTo: webViewContainer.leftAnchor).isActive = true
        webView.bottomAnchor.constraint(equalTo: webViewContainer.bottomAnchor).isActive = true
        webView.heightAnchor.constraint(equalTo: webViewContainer.heightAnchor).isActive = true
        
        webView.uiDelegate = self
        webView.navigationDelegate = self
        webView.scrollView.bounces = false
        webView.allowsBackForwardNavigationGestures = true
        urlTextField.delegate = self
    }
    
    override func viewDidAppear(_ animated: Bool) {
        super.viewDidAppear(animated)
        
        self.openUrl(urlString)
        
        urlTextField.text = urlString
    }
    
    func openUrl(_ urlString: String) {
        let url = URL(string: urlString)
        let request = URLRequest(url: url!)
        webView.load(request)
    }
    
    func textFieldShouldReturn(_ textField: UITextField) -> Bool {
        let urlString:String = urlTextField.text!
        self.openUrl(urlString)
        
        textField.resignFirstResponder()
        return true
    }
    
    @IBAction func backButtonTapped(_ sender: Any) {
        if webView.canGoBack {
            webView.goBack()
        }
    }
    
    @IBAction func nextButtonTapped(_ sender: Any) {
        if webView.canGoForward {
            webView.goForward()
        }
    }
    
    func webView(_ webView: WKWebView, didCommit navigation: WKNavigation!) {
        urlTextField.text = webView.url?.absoluteString
    }
    
    func webView(_ webView: WKWebView, didFinish navigation: WKNavigation!) {
        backButton.isEnabled = webView.canGoBack
        nextButton.isEnabled = webView.canGoForward
    }
    
    func webView(_ webView: WKWebView, decidePolicyFor navigationAction: WKNavigationAction, decisionHandler: @escaping (WKNavigationActionPolicy) -> Void) {
        decisionHandler(WKNavigationActionPolicy.allow)
    }

    public func webView(_ webView: WKWebView, createWebViewWith configuration: WKWebViewConfiguration, for navigationAction: WKNavigationAction, windowFeatures: WKWindowFeatures) -> WKWebView? {
        guard let url: URL = navigationAction.request.url, let urlScheme = url.scheme else { return nil }
        if (urlScheme == "http" || urlScheme == "https") {
            UIApplication.shared.open(url)
            return nil
        }
        return nil
    }
    
    @IBAction func playButtonTapped(_ sender: Any) {
        webView.evaluateJavaScript("dtkPlayer.play();", completionHandler: nil)
    }
    
    @IBAction func pauseButtonTapped(_ sender: Any) {
        webView.evaluateJavaScript("dtkPlayer.pause();", completionHandler: nil)
    }
    
    @IBAction func stopButtonTapped(_ sender: Any) {
        webView.evaluateJavaScript("dtkPlayer.stop();", completionHandler: nil)
    }
    
    @IBAction func muteButtonTapped(_ sender: Any) {
        webView.evaluateJavaScript("dtkPlayer.toggleMute(true, true);", completionHandler: nil)
    }
    
    @IBAction func unmuteButtonTapped(_ sender: Any) {
        webView.evaluateJavaScript("dtkPlayer.toggleMute(false, true);", completionHandler: nil)
    }
}
