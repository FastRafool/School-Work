import { useState } from "react";
import axios from 'axios';
import {
    Button,
    Container,
    Header,
    Input,
    Multiselect,
    MultiselectProps,
    SpaceBetween,
    BreadcrumbGroup,
    ContentLayout,
    Box,
} from "@cloudscape-design/components";
import BaseAppLayout from "../../../components/base-app-layout";
import { useOnFollow } from "../../../common/hooks/use-on-follow";
import { APP_NAME } from "../../../common/constants";

function NewsletterPage() {
    const [selectedOptions, setSelectedOptions] = useState<MultiselectProps.Option[]>([]);
    const [email, setEmail] = useState("");

    const dropdownOptions: MultiselectProps.Options = [
        {
            label: "Frequency",
            options: [
                { label: "Daily", value: "daily" },
                { label: "Weekly", value: "weekly" },
                { label: "Monthly", value: "monthly" },
            ],
        },
        {
            label: "Keyword/Topic",
            options: [
                { label: "braket", value: "braket" },
                { label: "amazon", value: "amazon" },
                { label: "compilation", value: "compilation" },
                { label: "d-wave", value: "d-wave" },
                { label: "google", value: "google" },
                { label: "ibm q", value: "ibm q" },
                { label: "ibm", value: "ibm" },
                { label: "ionq", value: "ionq" },
                { label: "microsoft", value: "microsoft" },
                { label: "qaoa", value: "qaoa" },
                { label: "quantinuum", value: "quantinuum" },
                { label: "quera", value: "quera" },
                { label: "rigetti", value: "rigetti" },
                { label: "shadow tomography", value: "shadow tomography" },
                { label: "vqe", value: "vqe" },
            ],
        },
    ];

    const handleSubscribe = async () => {
        // Ensure option.value is defined before using it in includes
        const frequencyOption = selectedOptions.find(option => option.value && ['daily', 'weekly', 'monthly'].includes(option.value));
        const frequency = frequencyOption ? frequencyOption.value : ""; // Handle undefined value

        const keywords = selectedOptions
            .filter(option => option.value && ['braket', 'amazon', 'compilation', 'd-wave', 'google', 'ibm q', 'ibm', 'ionq', 'microsoft', 'qaoa', 'quantinuum', 'quera', 'rigetti', 'shadow tomography', 'vqe'].includes(option.value))
            .map(option => option.value || ""); // Handle undefined value and ensure no undefined values are included

        if (!email || !frequency || keywords.length === 0) {
            alert('Please ensure you have entered your email, selected a frequency, and at least one keyword.');
            return;
        }

        const postData = {
            email,
            frequency,
            keywords
        };

        try {
            const response = await axios.post('https://pe7l3c89kl.execute-api.us-east-1.amazonaws.com/dev/subs', postData, {
                headers: {
                    'Content-Type': 'application/json'
                }
            });
            alert('Subscription successful!');
            console.log(response.data); // Logging the response data
        } catch (error: any) { // Handling errors with a type assertion
            if (error.response) {
                // Server responded with a status other than 2xx
                console.error('Subscription error:', error.response.data);
                alert('Failed to subscribe. Please try again.');
            } else if (error.request) {
                // Request was made but no response received
                console.error('Subscription error: No response received.');
                alert('Subscription successful!');
            } else {
                // Something happened in setting up the request
                console.error('Subscription error:', error.message);
                alert('Failed to subscribe. Please try again.');
            }
        }
    };

    return (
        <BaseAppLayout
            breadcrumbs={
                <BreadcrumbGroup
                    onFollow={useOnFollow}
                    items={[
                        {
                            text: APP_NAME,
                            href: "/",
                        },
                        {
                            text: "Newsletter",
                            href: "/section2/item1",
                        },
                    ]}
                />
            }
            content={
                <ContentLayout header={<Header>Newsletter</Header>}>
                    <SpaceBetween size="l">
                        <Header
                            variant="h1"
                            description="Get daily, weekly, or monthly updates on mentions of AWS Braket from Arxiv right in your mailbox."
                            
                        >
                            GET OUR NEWSLETTER!
                        </Header>
                        <Container>
                            <Box padding={{ vertical: 's', horizontal: 'm' }}>
                                <p><strong>Frequency and Keyword options for your emails.</strong></p>
                                <p>This will give you a choice of what content you receive and how often you will receive emails from us.</p>
                            </Box>
                            <SpaceBetween size="m">
                                <Multiselect
                                    placeholder="Choose options"
                                    selectedOptions={selectedOptions}
                                    onChange={(event) =>
                                        setSelectedOptions([...event.detail.selectedOptions] as MultiselectProps.Option[])
                                    }
                                    options={dropdownOptions}
                                />
                                <Input
                                    type="email"
                                    placeholder="Email Address"
                                    value={email}
                                    onChange={(event) => setEmail(event.detail.value)}
                                />
                                <Button variant="primary" onClick={handleSubscribe}>SUBSCRIBE</Button>
                            </SpaceBetween>
                        </Container>
                    </SpaceBetween>
                </ContentLayout>
            }
        />
    );
}

export default NewsletterPage;
